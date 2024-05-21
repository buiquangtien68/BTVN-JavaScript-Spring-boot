package com.example.demo.repository;

import com.example.demo.entities.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query("SELECT DISTINCT f FROM Favorite f JOIN f.movie m JOIN f.user u WHERE u.id = ?1 AND m.status=true ORDER BY f.createdAt DESC")
     Page<Favorite> findByUser_IdOrderByCreatedAtDesc(Integer userId, PageRequest pageRequest);

    @Query("SELECT DISTINCT f FROM Favorite f JOIN f.movie m JOIN f.user u WHERE u.id = ?1 AND m.status=true ORDER BY f.createdAt DESC")
    List<Favorite> findByUser_IdOrderByCreatedAtDesc(Integer userId);

    @Query("SELECT DISTINCT f FROM Favorite f JOIN f.movie m JOIN f.user u WHERE m.id = ?1 AND  u.id =?2 AND m.status=true ")
    Optional<Favorite> findByMovie_IdAndUser_Id(Integer movieid, Integer id);
}
