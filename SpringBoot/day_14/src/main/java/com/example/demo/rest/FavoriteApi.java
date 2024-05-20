package com.example.demo.rest;

import com.example.demo.entities.Favorite;
import com.example.demo.entities.Review;
import com.example.demo.model.request.FavoriteRequest;
import com.example.demo.service.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<?> createFavorite(@Valid @RequestBody FavoriteRequest favoriteRequest) {
        Favorite favorite = favoriteService.createFavorite(favoriteRequest);
        return new ResponseEntity<>(favorite, HttpStatus.CREATED); //201
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Integer id) {
        favoriteService.deleteReview(id);
        return ResponseEntity.noContent().build(); //204
    }
}
