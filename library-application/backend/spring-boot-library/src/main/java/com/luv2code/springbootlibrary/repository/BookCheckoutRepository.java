package com.luv2code.springbootlibrary.repository;


import com.luv2code.springbootlibrary.entity.BookCheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCheckoutRepository extends JpaRepository<BookCheckoutEntity, Long> {

    BookCheckoutEntity findByUserEmailAndBookId(String userEmail, Long bookId);

    List<BookCheckoutEntity> findBooksByUserEmail(String userEmail);

    @Modifying
    @Query("delete from BookCheckoutEntity where id in :book_id")
    void deleteAllByBookId(@Param("book_id") Long bookId);
}
