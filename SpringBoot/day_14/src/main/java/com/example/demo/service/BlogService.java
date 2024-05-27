package com.example.demo.service;

import com.example.demo.entities.Blog;
import com.example.demo.entities.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.enums.MovieType;
import com.example.demo.model.request.UpsertBlogRequest;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import com.github.slugify.Slugify;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private FileService fileService;

    public Page<Blog> getBlogByStatus(Boolean status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page-1, pageSize, Sort.by("createdAt").descending());
        return blogRepository.findByStatus(status,pageRequest);
    }
    public Blog getBlogByIdAndSlugAndStatus(Integer id, String slug, Boolean status) {
        return blogRepository.findByIdAndSlugAndStatus(id, slug, status);
    }
    public List<Blog> getAll() {
        return blogRepository.findAll(Sort.by("createdAt").descending());
    }

    public List<Blog> getAllByUserIdOrderByCreatedAtDesc() {
     User user = (User) httpSession.getAttribute("user");
        return blogRepository.findAllByUserIdOrderByCreatedAtDesc(user.getId());
    }
    public Blog getBlogById(Integer id) {
        if (blogRepository.findById(id).isPresent()) {
            return blogRepository.findById(id).get();
        }
       return null;
    }

    public Blog createBlog(UpsertBlogRequest blogRequest) {
        User user = (User) httpSession.getAttribute("user");

        Slugify slugify= Slugify.builder().build();
        Blog blog = Blog.builder()
                .title(blogRequest.getTitle())
                .slug(slugify.slugify(blogRequest.getTitle()))
                .content(blogRequest.getContent())
                .description(blogRequest.getDescription())
                .status(blogRequest.getStatus())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .user(user)
                .build();
        blogRepository.save(blog);
        return blog;
    }

    public Blog updateBlog(UpsertBlogRequest blogRequest, Integer id) {
        //Kiểm tra blog có tồn tại hay không
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        User user = (User) httpSession.getAttribute("user");

        //Kiểm tra xem blog này có thuộc về user này không
        if (!blog.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("User not authorized to update blog");
        }
        Slugify slugify= Slugify.builder().build();
        blog.setTitle(blogRequest.getTitle());
        blog.setSlug(slugify.slugify(blog.getTitle()));
        blog.setContent(blogRequest.getContent());
        blog.setDescription(blogRequest.getDescription());
        blog.setStatus(blogRequest.getStatus());
        blog.setUpdatedAt(LocalDate.now());
        blogRepository.save(blog);
        return blog;
    }

    public void deleteBlog(Integer id) {
        //Kiểm tra blog có tồn tại hay không
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        User user = (User) httpSession.getAttribute("user");

        //Kiểm tra xem blog này có thuộc về user này không
        if (!blog.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("User not authorized to update blog");
        }

        blogRepository.delete(blog);
    }

    public String uploadThumbnail(Integer id, MultipartFile file) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
        try {
            Map data = fileService.uploadFile(file);
            String url = (String) data.get("url");
            blog.setThumbnail(url);
            blogRepository.save(blog);

            return url;
        }catch (IOException e) {
            throw new RuntimeException("Error while uploading thumbnail");
        }
    }
}
