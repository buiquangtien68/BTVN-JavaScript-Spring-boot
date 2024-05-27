package com.example.demo.service;

import com.example.demo.entities.Director;
import com.example.demo.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public List<Director> getAllDirectorById(List<Integer> ids) {
        return directorRepository.findAllById(ids);
    }
}
