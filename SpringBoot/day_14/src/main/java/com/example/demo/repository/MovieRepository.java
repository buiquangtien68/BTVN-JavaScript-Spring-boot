package com.example.demo.repository;

import com.example.demo.entities.Movie;
import com.example.demo.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //tìm kiếm
    List<Movie> findByName(String name);
    List<Movie> findByNameIgnoreCase (String name);
    List<Movie> findByNameContaining (String keyword);
    List<Movie> findByNameAndSlug (String name, String slug);
    List<Movie> findByRatingBetween  (Double min, Double max);
    List<Movie> findByRatingLessThanEqual (Double max);
    List<Movie> findByCreatedAtAfter (LocalDate createdAt);
    //sort
    List<Movie> findByTypeOrderByRatingDesc(MovieType type);
    List<Movie> findByType(MovieType type, Sort sort);
    Movie findFirstByTypeOrderByRatingDesc(MovieType type);
    List<Movie> findByTypeAndStatusOrderByCreatedAtDesc(MovieType type, Boolean status);
    //đếm số lượng
    long countByStatus (Boolean status);
    //Kểm tra tồn tại
    boolean existsByName(String name);

    List<Movie> findByStatus(Boolean status);

    //Phân trang
   Page<Movie> findByStatus(boolean status, Pageable pageable);
   Page<Movie> findByTypeAndStatus (MovieType type,Boolean status, Pageable pageable);

   List<Movie> findByStatusOrderByRatingDesc(Boolean status);

    Movie findByIdAndSlugAndStatus(Integer id, String slug, boolean status);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g WHERE g.name = ?1 AND m.status=true AND m.id NOT IN ?2 ORDER BY m.rating DESC")
    List<Movie> findByGenreNameOrderByRatingDescExcludingMovieId(String genreName, Integer excludedMovieId);

    Optional<Movie> findById(Integer id);

    @Query("SELECT DISTINCT m FROM Movie m WHERE m.createdAt BETWEEN ?1 AND ?2")
    List<Movie> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

}
