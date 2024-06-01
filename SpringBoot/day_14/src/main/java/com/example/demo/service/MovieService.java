package com.example.demo.service;

import com.example.demo.entities.Blog;
import com.example.demo.entities.Country;
import com.example.demo.entities.Movie;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.dataChart.MovieData;
import com.example.demo.model.enums.MovieType;
import com.example.demo.model.request.UpsertMovieRequest;
import com.example.demo.repository.*;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ActorService actorService;
    @Autowired
    DirectorService directorService;
    @Autowired
    GenreService genreService;
    @Autowired
    CountryService countryService;
    @Autowired
    FileService fileService;


    public List<Movie> findByTypeAndStatusOrderByCreatedAtDesc(MovieType type, Boolean status) {
        return movieRepository.findByTypeAndStatusOrderByCreatedAtDesc(type,status);
    }

    public Page<Movie> getMovieByType(MovieType type, Boolean status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page-1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findByTypeAndStatus(type,status,pageRequest);
    }

    public List<Movie> findByStatus(Boolean status) {
        return movieRepository.findByStatusOrderByRatingDesc(status).stream().limit(4).toList();
    }
    public Movie getMovieByIdAndSlugAndStatus (Integer id, String slug, Boolean status) {
        return movieRepository.findByIdAndSlugAndStatus(id,slug,status);
    }
    public List<Movie> findByGenreNameOrderByRatingDescExcludingMovieId(String genreName, Integer excludedMovieId) {
        return movieRepository.findByGenreNameOrderByRatingDescExcludingMovieId(genreName,excludedMovieId).stream().limit(8).toList();
    }
    public Optional<Movie> getOptionalMovieById(Integer id) {
            return movieRepository.findById(id);
    }

    public Movie getMovieById(Integer id) {
        if (movieRepository.findById(id).isPresent()) {
            return movieRepository.findById(id).get();
        }
        return null;
    }

    public Page<Movie> getFavoriteMovie(MovieType type, Boolean status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page-1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findByTypeAndStatus(type,status,pageRequest);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll(Sort.by("createdAt").descending());
    }


    //tạo, sửa, xóa Movie
    public Movie createMovie(UpsertMovieRequest upsertMovieRequest) {
        Slugify slugify= Slugify.builder().build();
        Movie movie = Movie.builder()
                .name(upsertMovieRequest.getName())
                .slug(slugify.slugify(upsertMovieRequest.getName()))
                .description(upsertMovieRequest.getDescription())
                .releaseYear(upsertMovieRequest.getReleaseYear())
                .type(upsertMovieRequest.getMovieType())
                .status(upsertMovieRequest.getStatus())
                .trailer(upsertMovieRequest.getTrailer())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .country(countryService.getCountryById(upsertMovieRequest.getCountryId()))
                .actors(actorService.getAllActorById(upsertMovieRequest.getActorIds()))
                .directors(directorService.getAllDirectorById(upsertMovieRequest.getDirectorIds()))
                .genres(genreService.getAllGenreById(upsertMovieRequest.getGenreIds()))
                .build();
        movieRepository.save(movie);
        return movie;
    }

    public Movie updateMovie(UpsertMovieRequest upsertMovieRequest, Integer id) {
        //Kiểm tra movie có tồn tại hay không
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie not found"));

        Slugify slugify= Slugify.builder().build();
        movie.setName(upsertMovieRequest.getName());
        movie.setSlug(slugify.slugify(upsertMovieRequest.getName()));
        movie.setDescription(upsertMovieRequest.getDescription());
        movie.setReleaseYear(upsertMovieRequest.getReleaseYear());
        movie.setType(upsertMovieRequest.getMovieType());
        movie.setTrailer(upsertMovieRequest.getTrailer());
        movie.setUpdatedAt(LocalDate.now());
        movie.setCountry(countryService.getCountryById(upsertMovieRequest.getCountryId()));
        movie.setActors(actorService.getAllActorById(upsertMovieRequest.getActorIds()));
        movie.setDirectors(directorService.getAllDirectorById(upsertMovieRequest.getDirectorIds()));
        movie.setGenres(genreService.getAllGenreById(upsertMovieRequest.getGenreIds()));
        movieRepository.save(movie);
        return movie;
    }

    public void deleteMovie(Integer id) {
        //Kiểm tra movie có tồn tại hay không
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie not found"));

        movieRepository.delete(movie);
    }

    public String uploadPoster(Integer id, MultipartFile file) {
        //Kiểm tra movie có tồn tại hay không
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie not found"));
        try {
            Map data = fileService.uploadFile(file);
            String url = (String) data.get("url");
            movie.setPoster(url);
            movieRepository.save(movie);

            return url;
        }catch (IOException e) {
            throw new RuntimeException("Error while uploading poster");
        }
    }

    public List<Movie> getMovieInCurrentMonth() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        return movieRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public List<MovieData> getMovieDataInFiveMonthsNearly() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now().minusMonths(4).withDayOfMonth(1);
        // Khởi tạo Map để lưu trữ dữ liệu của các tháng
        Map<YearMonth, Integer> movieCountByMonth = new HashMap<>();

        // Khởi tạo tất cả các tháng trong khoảng thời gian với số lượng phim ban đầu là 0
        YearMonth currentMonth = YearMonth.from(startDate);
        while (!currentMonth.isAfter(YearMonth.from(endDate))) {
            movieCountByMonth.put(currentMonth, 0);
            currentMonth = currentMonth.plusMonths(1);
        }

        // Lấy danh sách các bộ phim trong khoảng thời gian đã cho
        movieRepository.findByCreatedAtBetween(startDate, endDate).forEach(movie -> {
            YearMonth movieYearMonth = YearMonth.from(movie.getCreatedAt());
            // Tăng số lượng phim cho tháng tương ứng
            movieCountByMonth.put(movieYearMonth, movieCountByMonth.getOrDefault(movieYearMonth, 0) + 1);
        });

        // Chuyển dữ liệu từ Map sang danh sách MovieData
        List<MovieData> movieDataList = new ArrayList<>();
        movieCountByMonth.forEach((yearMonth, movieCount) -> {
            movieDataList.add(MovieData.builder()
                    .year(yearMonth.getYear())
                    .month(yearMonth.getMonthValue())
                    .movieCount(movieCount)
                    .build());
        });
        // Sắp xếp theo ngày tăng dần
        movieDataList.sort(Comparator.comparing(movieData -> YearMonth.of(movieData.getYear(), movieData.getMonth())));

        return movieDataList;
    }

}
