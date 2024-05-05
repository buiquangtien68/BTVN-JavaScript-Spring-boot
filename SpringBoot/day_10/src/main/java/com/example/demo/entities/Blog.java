package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String title;
    String slug;
    String description;
    @Column(nullable = false)
    String content;
    String thumbnail;
    Boolean status;
    LocalDate createdAt;
    LocalDate updatedAt;
}
