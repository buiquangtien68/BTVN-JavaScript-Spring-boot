package com.example.demo.repository;

import com.example.demo.entities.Blog;
import com.example.demo.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByStatus(boolean status, Pageable pageable);

    Blog findByIdAndSlugAndStatus(Integer id, String slug, boolean status);

    List<Blog> findAllByUserIdOrderByCreatedAtDesc(Integer userId);

    @Query("SELECT DISTINCT b FROM Blog b WHERE b.createdAt BETWEEN ?1 AND ?2")
    List<Blog> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

}
