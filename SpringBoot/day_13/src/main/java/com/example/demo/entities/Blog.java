package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String title;
    String slug;
    @Column(columnDefinition = "TEXT")
    String description;
    @Column(nullable = false,columnDefinition = "TEXT")
    String content;
    String thumbnail;
    Boolean status;
    LocalDate createdAt;
    LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name ="user_id")
    User user;
}
