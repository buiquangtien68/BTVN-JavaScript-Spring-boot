package com.example.demo.repository;

import com.example.demo.entities.Episode;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovie_IdOrderByOrdersAsc(Integer movieId);

    Optional<Episode> findByMovie_IdAndMovie_StatusAndOrders(Integer movieId,Boolean status, Integer orders);
}
