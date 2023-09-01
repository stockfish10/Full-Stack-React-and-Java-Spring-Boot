package com.luv2code.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "History")
@Data
public class History {
    public History(){
    }

    public History(String userEmail, String checkoutDate, String returnDate,
                   String title, String author, String description, String image){
        this.userEmail = userEmail;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "checkout_date")
    private String checkoutDate;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;
}
