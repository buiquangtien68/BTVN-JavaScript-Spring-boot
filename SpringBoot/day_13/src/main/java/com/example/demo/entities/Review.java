package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false, columnDefinition = "TEXT")
    String content;
    @Column(nullable = false)
    Integer rating;
    LocalDate createdAt;
    LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name ="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name ="movie_id")
    Movie movie;

}
