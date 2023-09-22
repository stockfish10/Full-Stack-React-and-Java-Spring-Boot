package com.luv2code.springbootlibrary.service;

import com.luv2code.springbootlibrary.entity.BookEntity;
import com.luv2code.springbootlibrary.repository.BookCheckoutRepository;
import com.luv2code.springbootlibrary.repository.BookRepository;
import com.luv2code.springbootlibrary.repository.ReviewRepository;
import com.luv2code.springbootlibrary.requestmodels.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private BookRepository bookRepository;
    private ReviewRepository reviewRepository;
    private BookCheckoutRepository bookCheckoutRepository;

    @Autowired
    public AdminService(BookRepository bookRepository, ReviewRepository reviewRepository,
                        BookCheckoutRepository bookCheckoutRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.bookCheckoutRepository = bookCheckoutRepository;
    }

    public void increaseBookQuantity(Long bookId) throws Exception {
        Optional<BookEntity> book = bookRepository.findById(bookId);

        if (book.isEmpty()) {
            throw new Exception("Book not found.");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable()+1);
        book.get().setCopies(book.get().getCopies()+1);

        bookRepository.save(book.get());
    }
    public void decreaseBookQuantity(Long bookId) throws Exception {
        Optional<BookEntity> book = bookRepository.findById(bookId);

        if (book.isEmpty() || book.get().getCopiesAvailable() <= 0 || book.get().getCopies() <= 0) {
            throw new Exception("Book not found or quantity locked");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        book.get().setCopies(book.get().getCopies() - 1);

        bookRepository.save(book.get());
    }
    public void postBook(AddBookRequest addBookRequest) {
        BookEntity book = new BookEntity();

        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setDescription(addBookRequest.getDescription());
        book.setCopiesAvailable(addBookRequest.getCopies());
        book.setCopies(addBookRequest.getCopies());
        book.setCategory(addBookRequest.getCategory());
        book.setImg(addBookRequest.getImg());

        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) throws Exception {
        Optional<BookEntity> book = bookRepository.findById(bookId);

        if (book.isEmpty()) {
            throw new Exception("Book not found.");
        }

        bookRepository.delete(book.get());
        bookCheckoutRepository.deleteAllByBookId(bookId);
        reviewRepository.deleteAllByBookId(bookId);
    }
}
