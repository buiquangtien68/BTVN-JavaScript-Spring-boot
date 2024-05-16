package com.example.demo.repository;

import com.example.demo.entities.User;
import com.example.demo.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole role);
}
