package com.example.demo.service;

import com.example.demo.entities.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public List<Movie> findByTypeAndStatusOrderByCreatedAtDesc(MovieType type, Boolean status) {
        return movieRepository.findByTypeAndStatusOrderByCreatedAtDesc(type,status);
    }

    public Page<Movie> getMovieByType(MovieType type, Boolean status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page-1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findByTypeAndStatus(type,status,pageRequest);
    }

    public List<Movie> findByStatus(Boolean status) {
        return movieRepository.findByStatusOrderByRatingDesc(status).stream().limit(4).toList();
    }
    public Movie getMovieByIdAndSlugAndStatus (Integer id, String slug, Boolean status) {
        return movieRepository.findByIdAndSlugAndStatus(id,slug,status);
    }
}
