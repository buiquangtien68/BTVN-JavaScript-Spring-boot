package com.example.demo.controller;

import com.example.demo.entities.*;
import com.example.demo.model.enums.MovieType;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.DirectorRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MovieController {
    @Autowired
    EpisodeService episodeService;
    @Autowired
    MovieService movieService;
    @Autowired
    CountryService countryService;
    @Autowired
    DirectorService directorService;
    @Autowired
    ActorService actorService;
    @Autowired
    GenreService genreService;

    //admin
    @GetMapping("/admin/movies")
    public String getIndexPage(Model model) {
        model.addAttribute("movies",movieService.getAllMovies());
        return "admin/movie/movie-index";
    }

    @GetMapping("/admin/movies/{id}")
    public String getDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("movie",movieService.getMovieById(id));
        model.addAttribute("countries",countryService.getAllCountries());
        model.addAttribute("directors",directorService.getAllDirectors());
        model.addAttribute("actors",actorService.getAllActors());
        model.addAttribute("genres",genreService.getAllGenres());
        model.addAttribute("movieType",MovieType.values());
        model.addAttribute("episodes",episodeService.findByMovie_IdOrderByOrdersAsc(id));
        return "admin/movie/movie-detail";
    }

    @GetMapping("/admin/movies/create")
    public String getCreatePage(Model model) {
        //Trả về danh sách quốc gia, thể loại, đạo diễn, loại phim, diễn viên
        model.addAttribute("countries",countryService.getAllCountries());
        model.addAttribute("directors",directorService.getAllDirectors());
        model.addAttribute("actors",actorService.getAllActors());
        model.addAttribute("genres",genreService.getAllGenres());
        model.addAttribute("movieType",MovieType.values());
      return "admin/movie/movie-create";
    }
}
