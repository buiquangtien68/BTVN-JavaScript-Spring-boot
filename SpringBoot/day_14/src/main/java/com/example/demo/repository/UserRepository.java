package com.example.demo.repository;

import com.example.demo.entities.Movie;
import com.example.demo.entities.User;
import com.example.demo.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole role);

    Optional<User> findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u WHERE u.createdAt BETWEEN ?1 AND ?2")
    List<User> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);
}
