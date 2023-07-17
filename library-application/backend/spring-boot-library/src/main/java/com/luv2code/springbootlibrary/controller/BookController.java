package com.luv2code.springbootlibrary.controller;

import com.luv2code.springbootlibrary.entity.BookEntity;
import com.luv2code.springbootlibrary.service.BookService;
import com.luv2code.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(@RequestHeader(value = "Authorization") String token){
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @PutMapping("/secure/bookCheckoutEntities")
    public BookEntity checkoutBook(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        return bookService.checkoutBook(userEmail,bookId);
    }



}
