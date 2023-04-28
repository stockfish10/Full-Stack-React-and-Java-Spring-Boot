package com.luv2code.springbootlibrary.repository;


import com.luv2code.springbootlibrary.entity.BookCheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCheckoutRepository extends JpaRepository<BookCheckoutEntity, Long> {

    BookCheckoutEntity findByUserEmailAndBookId(String userEmail, Long bookId);
}
