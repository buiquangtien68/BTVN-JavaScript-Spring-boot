package com.example.demo.repository;

import com.example.demo.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByMovie_IdOrderByCreatedAtDesc(Integer movieId);
}
