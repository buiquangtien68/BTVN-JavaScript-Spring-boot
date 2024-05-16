package com.example.demo.entities;

import com.example.demo.model.enums.MovieType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Movie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer id;

    String poster;
    @Column(nullable = false)
    String name;
    String slug;
    @Column(nullable = false)
    Integer releaseYear;
    @Column(columnDefinition = "TEXT")
    String description;
    Double rating;
    MovieType type;
    Boolean status;
    String trailer;

    LocalDate createdAt;
    LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    List<Actor> actors;

    @ManyToMany
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    List<Director> directors;
}
