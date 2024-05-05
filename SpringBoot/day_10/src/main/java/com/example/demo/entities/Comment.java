package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 50)
    String content;
    LocalDate createdAt;
    LocalDate updatedAt;
}
