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
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name ="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name ="movie_id")
    Movie movie;
}
