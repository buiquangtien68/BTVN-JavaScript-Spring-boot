package com.example.demo.service;

import com.example.demo.entities.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.reponsitory.MovieReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieReponsitory movieReponsitory;
    public List<Movie> findByTypeAndStatusOrderByCreatedAtDesc(MovieType type, Boolean status) {
        return movieReponsitory.findByTypeAndStatusOrderByCreatedAtDesc(type,status);
    }
}
