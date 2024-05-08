package com.example.demo.controller;

import com.example.demo.model.enums.MovieType;
import com.example.demo.reponsitory.MovieReponsitory;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("/phim-bo")
    public String phimBo(Model model) {
        model.addAttribute("movies", movieService.findByTypeAndStatusOrderByCreatedAtDesc(MovieType.PHIM_BO,true));
        return "phim-bo";
    }
    @GetMapping("/phim-le")
    public String phimLe(Model model) {
        model.addAttribute("movies", movieService.findByTypeAndStatusOrderByCreatedAtDesc(MovieType.PHIM_LE,true));
        return "phim-le";
    }
    @GetMapping("/phim-chieu-rap")
    public String phimChieuRap(Model model) {
        model.addAttribute("movies", movieService.findByTypeAndStatusOrderByCreatedAtDesc(MovieType.PHIM_CHIEU_RAP,true));
        return "phim-chieu-rap";
    }
}
