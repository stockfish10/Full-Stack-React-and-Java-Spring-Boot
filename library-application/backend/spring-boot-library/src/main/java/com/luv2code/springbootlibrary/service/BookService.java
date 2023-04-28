package com.luv2code.springbootlibrary.service;

import com.luv2code.springbootlibrary.entity.BookCheckoutEntity;
import com.luv2code.springbootlibrary.entity.BookEntity;
import com.luv2code.springbootlibrary.repository.BookCheckoutRepository;
import com.luv2code.springbootlibrary.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;
    private BookCheckoutRepository bookCheckoutRepository;

    public BookService(BookRepository bookRepository, BookCheckoutRepository bookCheckoutRepository) {
        this.bookRepository = bookRepository;
        this.bookCheckoutRepository = bookCheckoutRepository;
    }

    public BookEntity checkoutBook (String userEmail, Long bookId) throws Exception {
        Optional<BookEntity> book = bookRepository.findById(bookId);

        BookCheckoutEntity validateCheckout = bookCheckoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if (!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0){
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


}


























