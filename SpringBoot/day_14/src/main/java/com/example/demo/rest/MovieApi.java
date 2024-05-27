package com.example.demo.rest;


import com.example.demo.entities.Movie;
import com.example.demo.model.request.UpsertMovieRequest;
import com.example.demo.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/movies")
public class MovieApi {
    @Autowired
    private MovieService movieService;
    @PostMapping
    public ResponseEntity<?> createMovie(@Valid @RequestBody UpsertMovieRequest upsertMovieRequest) {
        Movie movie = movieService.createMovie(upsertMovieRequest);
        return new ResponseEntity<>(movie, HttpStatus.CREATED); //201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@Valid @PathVariable Integer id, @Valid @RequestBody UpsertMovieRequest upsertMovieRequest) {
        Movie movie = movieService.updateMovie(upsertMovieRequest, id);
        return ResponseEntity.ok(movie); //200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build(); //204
    }

    @PostMapping("/{id}/upload-poster")
    public ResponseEntity<?> uploadThumbnail( @PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(movieService.uploadPoster(id,file));
    }
}
