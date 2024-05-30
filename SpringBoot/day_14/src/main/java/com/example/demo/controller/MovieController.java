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
    MovieService movieService;
    @Autowired
    BlogService blogService;
    @Autowired
    EpisodeService episodeService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    private HttpSession httpSession;


    @Autowired
    CountryService countryService;
    @Autowired
    DirectorService directorService;
    @Autowired
    ActorService actorService;
    @Autowired
    GenreService genreService;


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
        return "web/index";
    }

    @GetMapping("/phim-bo")
    public String phimBo(Model model, @RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "12") int pageSize) {
        Page<Movie> pageData =movieService.getMovieByType(MovieType.PHIM_BO,true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String phimLe(Model model,@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "12") int pageSize) {
        Page<Movie> pageData =movieService.getMovieByType(MovieType.PHIM_LE,true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String phimChieuRap(Model model,@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "12") int pageSize) {
        Page<Movie> pageData =movieService.getMovieByType(MovieType.PHIM_CHIEU_RAP,true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String phim(@PathVariable int id, @PathVariable String slug, Model model) {
       Random random = new Random();
        model.addAttribute("movie",movieService.getMovieByIdAndSlugAndStatus(id,slug,true));
        List<MovieType> movieTypes = Arrays.asList(MovieType.values());
        model.addAttribute("movieTypes", movieTypes);
        model.addAttribute("episodes",episodeService.findByMovie_IdOrderByOrdersAsc(id));
        model.addAttribute("reviews",reviewService.findByMovie_IdOrderByCreatedAtDesc(id));
        Optional<Movie> movie = movieService.getOptionalMovieById(id);
        if(movie.isPresent()) {
            List<Genre> genres =movie.get().getGenres();
            String rdGenre = genres.get(random.nextInt(genres.size())).getName();
            model.addAttribute("ListPhimDeCu",movieService.findByGenreNameOrderByRatingDescExcludingMovieId(rdGenre,id));
        }
        if (httpSession.getAttribute("user") != null) {
            User user = (User) httpSession.getAttribute("user");
            model.addAttribute("favorites", favoriteService.findByUser_IdOrderByCreatedAtDesc(user.getId()));
            Favorite favorite = favoriteService.findByUser_IdOrderByCreatedAtDesc(user.getId()).stream()
                    .filter(f -> f.getMovie().getId() == id)
                    .findFirst()
                    .orElse(null); // hoặc giá trị mặc định khác nếu cần
            model.addAttribute("favorite", favorite);
        }

       return "web/chi-tiet-phim";
    }

    @GetMapping("/dang-nhap")
    public String dangNhap(Model model) {
       return "web/dang-nhap";
    }
    @GetMapping("/dang-ky")
    public String dangKy(Model model) {
       return "web/dang-ky";
    }
    @GetMapping("/thong-tin-ca-nhan")
    public String thongTinCaNhan(Model model) {
        return "web/thong-tin-ca-nhan";
    }
    @GetMapping("/doi-mat-khau")
    public String doiMatKhau(Model model) {
        return "web/doi-mat-khau";
    }


    @GetMapping("/phim-yeu-thich")
    public String phimYeuThich(Model model,@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "12") int pageSize) {
        Page<Favorite> pageData =favoriteService.findByUser_IdOrderByCreatedAtDesc(page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/phim-yeu-thich";
    }


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
