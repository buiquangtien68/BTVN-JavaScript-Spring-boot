package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String videoURL;
    Double duration;
    Integer order;
    LocalDate createdAt;
    LocalDate updatedAt;
}
