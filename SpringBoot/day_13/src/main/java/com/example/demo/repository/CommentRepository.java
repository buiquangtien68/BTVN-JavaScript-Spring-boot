package com.example.demo.repository;

import com.example.demo.entities.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByBlog_IdOrderByCreatedAtDesc(Integer blogId);
}
