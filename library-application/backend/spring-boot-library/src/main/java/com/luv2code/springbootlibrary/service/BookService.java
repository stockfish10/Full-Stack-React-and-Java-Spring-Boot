package com.luv2code.springbootlibrary.service;

import com.luv2code.springbootlibrary.entity.BookCheckoutEntity;
import com.luv2code.springbootlibrary.entity.BookEntity;
import com.luv2code.springbootlibrary.repository.BookCheckoutRepository;
import com.luv2code.springbootlibrary.repository.BookRepository;
import com.luv2code.springbootlibrary.responsemodels.ShelfCurrentLoansResponse;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;
    private BookCheckoutRepository bookCheckoutRepository;

    public BookService(BookRepository bookRepository, BookCheckoutRepository bookCheckoutRepository) {
        this.bookRepository = bookRepository;
        this.bookCheckoutRepository = bookCheckoutRepository;
    }

    public BookEntity checkoutBook(String userEmail, Long bookId) throws Exception {
        Optional<BookEntity> book = bookRepository.findById(bookId);

        BookCheckoutEntity validateCheckout = bookCheckoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if (!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book doesn't exist or already checked out by user");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());

        BookCheckoutEntity checkout = new BookCheckoutEntity(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId());

        bookCheckoutRepository.save(checkout);

        return book.get();
    }

    public Boolean checkoutBookByUser(String userEmail, Long bookId) {
        BookCheckoutEntity validateCheckout = bookCheckoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        return validateCheckout != null;
    }

    public int currentLoansCount(String userEmail) {
        return bookCheckoutRepository.findBooksByUserEmail(userEmail).size();
    }

    public List<ShelfCurrentLoansResponse> currentLoans(String userEmail) throws Exception {

        List<ShelfCurrentLoansResponse> shelfCurrentLoansResponses = new ArrayList<>();
        List<BookCheckoutEntity> checkoutList = bookCheckoutRepository.findBooksByUserEmail(userEmail);
        List<Long> bookIdList = new ArrayList<>();

        for (BookCheckoutEntity checkout : checkoutList) {
            bookIdList.add(checkout.getBookId());
        }

        List<BookEntity> books = bookRepository.findBooksByBookIds(bookIdList);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (BookEntity book : books) {
            Optional<BookCheckoutEntity> checkout = checkoutList.stream().
                    filter(b -> b.getBookId() == book.getId()).findFirst();

            if (checkout.isPresent()) {
                Date d1 = sdf.parse(checkout.get().getReturnDate());
                Date d2 = sdf.parse(LocalDate.now().toString());

                TimeUnit time = TimeUnit.DAYS;

                long differenceInTime = time.convert(d1.getTime() - d2.getTime(), TimeUnit.MICROSECONDS);

                shelfCurrentLoansResponses.add(new ShelfCurrentLoansResponse(book, (int) differenceInTime));
            }
        }

        return shelfCurrentLoansResponses;
    }

    public void returnBook(String userEmail, Long bookId) throws Exception {
        Optional<BookEntity> book = bookRepository.findById(bookId);

        BookCheckoutEntity validateCheckout = bookCheckoutRepository.findByUserEmailAndBookId(userEmail,bookId);

        if (book.isEmpty() || validateCheckout == null) {
            throw new Exception("Book does not exist or not checked out by user!");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() + 1);

        bookRepository.save(book.get());

        bookCheckoutRepository.deleteById(validateCheckout.getId());
    }

    public void renewLoan(String userEmail, Long bookId) throws Exception {

        BookCheckoutEntity validateCheckout = bookCheckoutRepository.findByUserEmailAndBookId(userEmail,bookId);

        if (validateCheckout == null) {
            throw new Exception("Book does not exist or not checked out by user!");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = sdf.parse(validateCheckout.getReturnDate());
        Date d2 = sdf.parse(LocalDate.now().toString());

        if (d1.compareTo(d2) > 0 || d1.compareTo(d2) == 0) {
            validateCheckout.setReturnDate(LocalDate.now().plusDays(7).toString());
        }

        bookCheckoutRepository.save(validateCheckout);

    }


}


























