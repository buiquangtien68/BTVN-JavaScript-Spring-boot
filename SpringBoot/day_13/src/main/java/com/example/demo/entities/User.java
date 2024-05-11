package com.example.demo.entities;

import com.example.demo.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;
    String avatar;

    @Enumerated(EnumType.STRING)
    UserRole role;


    LocalDate createdAt;
    LocalDate updatedAt;
}
