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
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String videoURL;

    Double duration;
    Integer orders;
    LocalDate createdAt;
    LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name ="movie_id")
    Movie movie;
}
