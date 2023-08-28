package com.luv2code.springbootlibrary.responsemodels;

import com.luv2code.springbootlibrary.entity.BookEntity;
import lombok.Data;

import java.awt.print.Book;

@Data
public class ShelfCurrentLoansResponse {

    public ShelfCurrentLoansResponse(BookEntity book, int daysLeft) {
        this.book = book;
        this.daysLeft = daysLeft;
    }

    private BookEntity book;
    private int daysLeft;
}
