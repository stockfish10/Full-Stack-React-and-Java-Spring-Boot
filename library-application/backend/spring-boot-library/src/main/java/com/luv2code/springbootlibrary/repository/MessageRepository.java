package com.luv2code.springbootlibrary.repository;

import com.luv2code.springbootlibrary.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
