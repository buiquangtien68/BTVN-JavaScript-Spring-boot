package com.example.demo.rest;

import com.example.demo.entities.Episode;
import com.example.demo.entities.Movie;
import com.example.demo.model.request.UpsertEpisodeRequest;
import com.example.demo.model.request.UpsertMovieRequest;
import com.example.demo.service.EpisodeService;
import com.example.demo.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/episodes")
public class EpisodeApi {
    @Autowired
    private EpisodeService episodeService;
    @PostMapping
    public ResponseEntity<?> createEpisode(@Valid @RequestBody UpsertEpisodeRequest upsertEpisodeRequest) {
        Episode episode = episodeService.createEpisode(upsertEpisodeRequest);
        return new ResponseEntity<>(episode, HttpStatus.CREATED); //201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEpisode(@Valid @PathVariable Integer id, @Valid @RequestBody UpsertEpisodeRequest upsertEpisodeRequest) {
        Episode episode = episodeService.updateEpisode(upsertEpisodeRequest, id);
        return ResponseEntity.ok(episode); //200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id);
        return ResponseEntity.noContent().build(); //204
    }

    @PostMapping("/{id}/upload-video")
    public ResponseEntity<?> uploadVideo( @PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(episodeService.uploadVideo(id,file));
    }

}
