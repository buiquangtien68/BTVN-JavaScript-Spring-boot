package com.example.demo.controller;

import com.example.demo.service.BlogService;
import com.example.demo.service.MovieService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("moviesInCurrentMonth", movieService.getMovieInCurrentMonth());
        model.addAttribute("usersInCurrentMonth", userService.getUserInCurrentMonth());
        model.addAttribute("allMovies", movieService.getAllMovies());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("moviesDataInFiveMonths", movieService.getMovieDataInFiveMonthsNearly());
        model.addAttribute("userDataInFiveMonths", userService.getUserDataInFiveMonthsNearly());
        model.addAttribute("blogsInCurrentMonth", blogService.getBlogInCurrentMonth());
        model.addAttribute("allBlogs", blogService.getAllBlogs());
        return "admin/dashboard/dashboard";
    }
}
