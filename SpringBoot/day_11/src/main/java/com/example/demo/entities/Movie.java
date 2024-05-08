package com.example.demo.entities;

import com.example.demo.model.enums.MovieType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

}
