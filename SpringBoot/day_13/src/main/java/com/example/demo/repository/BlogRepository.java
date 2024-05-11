package com.example.demo.repository;

import com.example.demo.entities.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByStatus(boolean status, Pageable pageable);

    Blog findByIdAndSlugAndStatus(Integer id, String slug, boolean status);
}
