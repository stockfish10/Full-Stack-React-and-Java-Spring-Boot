package com.luv2code.springbootlibrary.repository;


import com.luv2code.springbootlibrary.entity.BookCheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCheckoutRepository extends JpaRepository<BookCheckoutEntity, Long> {

    BookCheckoutEntity findByUserEmailAndBookId(String userEmail, Long bookId);

    List<BookCheckoutEntity> findBooksByUserEmail(String userEmail);
}
