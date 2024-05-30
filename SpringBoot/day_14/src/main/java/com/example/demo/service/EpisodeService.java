package com.example.demo.service;

import com.example.demo.entities.Episode;
import com.example.demo.entities.Movie;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.request.UpsertEpisodeRequest;
import com.example.demo.model.request.UpsertMovieRequest;
import com.example.demo.repository.EpisodeRepository;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class EpisodeService {
    @Autowired
    EpisodeRepository episodeRepository;
    @Autowired
    MovieService movieService;
    @Autowired
    FileService fileService;

    public List<Episode> findByMovie_IdOrderByOrdersAsc(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByOrdersAsc(movieId);
    }

    public Episode createEpisode(UpsertEpisodeRequest upsertEpisodeRequest) {
        if (movieService.getMovieById(upsertEpisodeRequest.getMovieId())==null) {
            throw new ResourceNotFoundException("Movie not found");
        }
        Episode episode = Episode.builder()
                .movie(movieService.getMovieById(upsertEpisodeRequest.getMovieId()))
                .name(upsertEpisodeRequest.getName())
                .orders(upsertEpisodeRequest.getOrders())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();
        episodeRepository.save(episode);
        return episode;
    }

    public Episode updateEpisode (UpsertEpisodeRequest upsertEpisodeRequest, Integer id) {
        //Kiểm tra tồn tại ep này k
        Episode episode= episodeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Episode not found"));

        episode.setName(upsertEpisodeRequest.getName());
        episode.setOrders(upsertEpisodeRequest.getOrders());
        episode.setUpdatedAt(LocalDate.now());

        episodeRepository.save(episode);
        return episode;
    }

    public void deleteEpisode(Integer id) {
        //Kiểm tra tồn tại ep này k
        Episode episode= episodeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Episode not found"));

        episodeRepository.delete(episode);
    }
    public Map uploadVideo(Integer id, MultipartFile file) {
        //Kiểm tra ep có tồn tại hay không
        Episode episode = episodeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Episode not found"));
        try {
            Map data = fileService.uploadVideo(file);
            String url = (String) data.get("url");
            Double duration = (Double) data.get("duration");
            episode.setVideoURL(url);
            episode.setDuration(duration);
            episodeRepository.save(episode);

            return data;
        }catch (IOException e) {
            throw new RuntimeException("Error while uploading video");
        }
    }
}
