package com.luv2code.springbootlibrary.repository;

import com.luv2code.springbootlibrary.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    Page<ReviewEntity> findByBookId(@RequestParam("book_id") Long bookId,
                                    Pageable pageable);

    ReviewEntity findByUserEmailAndBookId(String userEmail, Long bookId);

    @Modifying
    @Query("delete from ReviewEntity where id in :book_id")
    void deleteAllByBookId(@Param("book_id") Long bookId);
}
