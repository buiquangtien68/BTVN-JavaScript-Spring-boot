package com.example.demo.controller;

import com.example.demo.entities.Blog;
import com.example.demo.entities.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.service.BlogService;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    MovieService movieService;
    @Autowired
    BlogService blogService;


   @GetMapping("/")
    public String index(Model model) {
       List<Blog> ListBlog =blogService.getBlogByStatus(true,1,4).getContent();
       List<Movie> listPhimHot =movieService.findByStatus(true);
       List<Movie> listPhimBo = movieService.getMovieByType(MovieType.PHIM_BO,true,1,6).getContent();
       List<Movie> listPhimLe = movieService.getMovieByType(MovieType.PHIM_LE,true,1,6).getContent();
       List<Movie> listPhimChieuRap = movieService.getMovieByType(MovieType.PHIM_CHIEU_RAP,true,1,6).getContent();
       model.addAttribute("ListPhimHot",listPhimHot);
       model.addAttribute("ListPhimBo",listPhimBo);
       model.addAttribute("ListPhimLe",listPhimLe);
       model.addAttribute("ListPhimChieuRap",listPhimChieuRap);
       model.addAttribute("ListBlog",ListBlog);
        return "index";
    }

    @GetMapping("/phim-bo")
    public String phimBo(Model model, @RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "12") int pageSize) {
        Page<Movie> pageData =movieService.getMovieByType(MovieType.PHIM_BO,true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "phim-bo";
    }

    @GetMapping("/phim-le")
    public String phimLe(Model model,@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "12") int pageSize) {
        Page<Movie> pageData =movieService.getMovieByType(MovieType.PHIM_LE,true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String phimChieuRap(Model model,@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "12") int pageSize) {
        Page<Movie> pageData =movieService.getMovieByType(MovieType.PHIM_CHIEU_RAP,true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String phim(@PathVariable int id, @PathVariable String slug, Model model) {
       model.addAttribute("movie",movieService.getMovieByIdAndSlugAndStatus(id,slug,true));
        List<MovieType> movieTypes = Arrays.asList(MovieType.values());
        model.addAttribute("movieTypes", movieTypes);
       return "chi-tiet-phim";
    }

}
