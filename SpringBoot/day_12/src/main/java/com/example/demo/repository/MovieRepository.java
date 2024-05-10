package com.example.demo.repository;

import com.example.demo.entities.Movie;
import com.example.demo.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

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

    //Phân trang
   Page<Movie> findByStatus(boolean status, Pageable pageable);
   Page<Movie> findByTypeAndStatus (MovieType type,Boolean status, Pageable pageable);

   List<Movie> findByStatusOrderByRatingDesc(Boolean status);

    Movie findByIdAndSlugAndStatus(Integer id, String slug, boolean status);

}
