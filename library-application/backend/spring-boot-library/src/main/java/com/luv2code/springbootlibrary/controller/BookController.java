package com.luv2code.springbootlibrary.controller;

import com.luv2code.springbootlibrary.entity.BookEntity;
import com.luv2code.springbootlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/bookEntities")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PutMapping("/secure/checkout")
    public BookEntity checkoutBook(@RequestParam Long bookId) throws Exception {
        String userEmail = "testuser@email.com";

        return bookService.checkoutBook(userEmail,bookId);
    }



}
