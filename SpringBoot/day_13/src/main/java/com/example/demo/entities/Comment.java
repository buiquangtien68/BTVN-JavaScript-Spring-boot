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
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "TEXT")
    String content;
    LocalDate createdAt;
    LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name ="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name ="blog_id")
    Blog blog;
}
