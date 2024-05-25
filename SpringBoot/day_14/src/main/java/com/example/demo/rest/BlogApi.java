package com.example.demo.rest;

import com.example.demo.entities.Blog;
import com.example.demo.entities.Review;
import com.example.demo.model.request.UpsertBlogRequest;
import com.example.demo.model.request.UpsertReviewRequest;
import com.example.demo.service.BlogService;
import com.example.demo.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/blogs")
public class BlogApi {
    @Autowired
    private BlogService blogService;

    @PostMapping
    public ResponseEntity<?> createBlog(@Valid @RequestBody UpsertBlogRequest blogRequest) {
        Blog blog = blogService.createBlog(blogRequest);
        return new ResponseEntity<>(blog, HttpStatus.CREATED); //201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@Valid @PathVariable Integer id, @Valid @RequestBody UpsertBlogRequest blogRequest) {
        Blog blog = blogService.updateBlog(blogRequest, id);
        return ResponseEntity.ok(blog); //200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build(); //204
    }

    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail( @PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(blogService.uploadThumbnail(id,file));
    }
}
