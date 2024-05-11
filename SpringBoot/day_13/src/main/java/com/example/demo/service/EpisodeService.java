package com.example.demo.service;

import com.example.demo.entities.Episode;
import com.example.demo.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {
    @Autowired
    EpisodeRepository episodeRepository;

    public List<Episode> findByMovie_IdOrderByOrdersAsc(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByOrdersAsc(movieId);
    }
}
