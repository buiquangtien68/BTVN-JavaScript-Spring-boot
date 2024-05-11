package com.example.demo.service;

import com.example.demo.entities.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findByBlog_IdOrderByCreatedAtDesc(Integer blogId) {
        return commentRepository.findByBlog_IdOrderByCreatedAtDesc(blogId);
    }
}
