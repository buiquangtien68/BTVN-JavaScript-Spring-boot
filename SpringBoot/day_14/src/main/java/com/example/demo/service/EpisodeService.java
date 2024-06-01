package com.example.demo.service;

import com.example.demo.entities.Episode;
import com.example.demo.entities.Movie;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.request.UpsertEpisodeRequest;
import com.example.demo.model.request.UpsertMovieRequest;
import com.example.demo.repository.EpisodeRepository;
import com.example.demo.repository.MovieRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class EpisodeService {
    @Autowired
    EpisodeRepository episodeRepository;
    @Autowired
    MovieService movieService;
    @Autowired
    FileService fileService;

    public List<Episode> findByMovie_IdOrderByOrdersAsc(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByOrdersAsc(movieId);
    }

    public Episode createEpisode(UpsertEpisodeRequest upsertEpisodeRequest) {
        if (movieService.getMovieById(upsertEpisodeRequest.getMovieId())==null) {
            throw new ResourceNotFoundException("Movie not found");
        }
        Episode episode = Episode.builder()
                .movie(movieService.getMovieById(upsertEpisodeRequest.getMovieId()))
                .name(upsertEpisodeRequest.getName())
                .orders(upsertEpisodeRequest.getOrders())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();
        episodeRepository.save(episode);
        return episode;
    }

    public Episode updateEpisode (UpsertEpisodeRequest upsertEpisodeRequest, Integer id) {
        //Kiểm tra tồn tại ep này k
        Episode episode= episodeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Episode not found"));

        episode.setName(upsertEpisodeRequest.getName());
        episode.setOrders(upsertEpisodeRequest.getOrders());
        episode.setUpdatedAt(LocalDate.now());

        episodeRepository.save(episode);
        return episode;
    }

    public void deleteEpisode(Integer id) {
        //Kiểm tra tồn tại ep này k
        Episode episode= episodeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Episode not found"));

        episodeRepository.delete(episode);
    }
    public Map uploadVideo(Integer id, MultipartFile file) {
        //Kiểm tra ep có tồn tại hay không
        Episode episode = episodeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Episode not found"));
        try {
            Map data = fileService.uploadVideo(file);
            String url = (String) data.get("url");
            Double duration = (Double) data.get("duration");
            episode.setVideoURL(url);
            episode.setDuration(duration);
            episodeRepository.save(episode);

            return data;
        }catch (IOException e) {
            throw new RuntimeException("Error while uploading video");
        }
    }

    public void deleteVideo(Integer id) throws IOException {
        Episode episode = episodeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Episode not found"));
        String publicId = extractPublicId(episode.getVideoURL());
        try {
            fileService.deleteVideo(publicId);
            episode.setVideoURL(null); // Clear the URL
            episode.setDuration(null); // Clear the duration
            episodeRepository.save(episode);
        } catch (IOException e) {
            throw new RuntimeException("Error while deleting video");
        }
    }
    private String extractPublicId(String videoURL) {
        // Kiểm tra xem URL có null hay không
        if (videoURL == null || videoURL.isEmpty()) {
            throw new IllegalArgumentException("Video URL is null or empty");
        }

        // Định nghĩa chuỗi bắt đầu của publicId trong URL
        String startMarker = "/upload/";
        // Tìm vị trí của chuỗi bắt đầu
        int startIndex = videoURL.indexOf(startMarker);

        // Kiểm tra xem chuỗi bắt đầu có tồn tại trong URL hay không
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid video URL");
        }

        // Tính toán vị trí kết thúc của publicId bằng cách tìm vị trí của ký tự '.' sau chuỗi bắt đầu
        int endIndex = videoURL.indexOf(".", startIndex + startMarker.length());

        // Kiểm tra xem vị trí kết thúc có hợp lệ không
        if (endIndex == -1) {
            throw new IllegalArgumentException("Invalid video URL");
        }

        // Trích xuất publicId từ URL bằng cách lấy phần substring từ startIndex đến endIndex
        String publicId = videoURL.substring(startIndex + startMarker.length(), endIndex);

        return publicId;
    }

    public Episode getEpisode(int movieId, String tap) {
        if (tap.equals("full")){
            return episodeRepository.findByMovie_IdAndMovie_StatusAndOrders(movieId,true,1).orElse(null);
        }else {
            return episodeRepository.findByMovie_IdAndMovie_StatusAndOrders(movieId,true,Integer.parseInt(tap)).orElse(null);
        }
    }
}
