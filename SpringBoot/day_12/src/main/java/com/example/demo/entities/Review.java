package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false, length = 50)
    String content;
    @Column(nullable = false)
    Integer rating;
    LocalDate createdAt;
    LocalDate updatedAt;
}
