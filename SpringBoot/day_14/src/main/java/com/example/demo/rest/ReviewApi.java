package com.example.demo.rest;

import com.example.demo.entities.Review;
import com.example.demo.model.request.UpsertReviewRequest;
import com.example.demo.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")//những api trả về json thì ghi rõ /api
public class ReviewApi {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@Valid @RequestBody UpsertReviewRequest reviewRequest) {
        Review review = reviewService.createReview(reviewRequest);
        return new ResponseEntity<>(review, HttpStatus.CREATED); //201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@Valid @PathVariable Integer id, @Valid @RequestBody UpsertReviewRequest reviewRequest) {
        Review review = reviewService.updateReview(reviewRequest, id);
        return ResponseEntity.ok(review); //200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build(); //204
    }
}
