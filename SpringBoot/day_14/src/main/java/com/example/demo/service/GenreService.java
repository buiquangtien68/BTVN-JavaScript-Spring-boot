package com.example.demo.service;

import com.example.demo.entities.Genre;
import com.example.demo.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public List<Genre> getAllGenreById(List<Integer> ids) {
        return genreRepository.findAllById(ids);
    }
}
