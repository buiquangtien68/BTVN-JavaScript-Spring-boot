package com.example.demo.service;

import com.example.demo.entities.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> findByMovie_IdOrderByCreatedAtDesc(Integer movieId) {
        return reviewRepository.findByMovie_IdOrderByCreatedAtDesc(movieId);
    }
}
