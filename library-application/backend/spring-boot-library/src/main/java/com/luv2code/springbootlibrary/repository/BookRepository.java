package com.luv2code.springbootlibrary.repository;

import com.luv2code.springbootlibrary.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {

    Page<BookEntity> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    Page<BookEntity> findByCategory(@RequestParam("category") String category, Pageable pageable);

    @Query("select o from BookEntity o where id in :book_ids")
    List<BookEntity> findBooksByBookIds (@Param("book_ids") List<Long> bookId);
}
