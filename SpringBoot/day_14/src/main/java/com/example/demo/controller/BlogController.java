package com.example.demo.controller;

import com.example.demo.entities.Blog;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;

    @GetMapping("/tin-tuc")
    public String tinTuc(Model model, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "4") int pageSize) {
        Page<Blog> pageData = blogService.getBlogByStatus(true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/tin-tuc";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String chiTietBlog(@PathVariable int id, @PathVariable String slug, Model model) {
        model.addAttribute("blog",blogService.getBlogByIdAndSlugAndStatus(id,slug,true));
        model.addAttribute("comments",commentService.findByBlog_IdOrderByCreatedAtDesc(id));
        return "web/chi-tiet-blog";
    }


    //admin
    @GetMapping("/admin/blogs")
    public String getIndexPage(Model model) {
        model.addAttribute("blogs", blogService.getAll());
        return "admin/blog/blog-index";
    }
    @GetMapping("/admin/blogs/own-blogs")
    public String getOwnBlogPage(Model model) {
        model.addAttribute("blogs", blogService.getAllByUserIdOrderByCreatedAtDesc());
        return "admin/blog/blog-yourself";
    }
    @GetMapping("/admin/blogs/create")
    public String getCreatePage() {
        return "admin/blog/blog-create";
    }
    @GetMapping("/admin/blogs/{id}")
    public String getDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("blog",blogService.getBlogById(id));
        return "admin/blog/blog-detail";
    }
}
