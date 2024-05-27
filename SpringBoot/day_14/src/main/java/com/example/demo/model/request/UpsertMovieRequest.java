package com.example.demo.model.request;
import com.example.demo.model.enums.MovieType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpsertMovieRequest {
    @NotBlank(message = "K đc để trống tên phim")
    String name;
    @NotBlank(message = "K đc để trống mô tả")
    String description;

    @NotNull(message = "Năm phát hành không được để trống")
    @Min(value = 1900, message = "Năm phát hành phải lớn hơn hoặc bằng 1900")
    @Max(value = 2100, message = "Năm phát hành phải nhỏ hơn hoặc bằng 2100")
    Integer releaseYear;
    MovieType movieType;
    Boolean status;
    @NotBlank(message = "K đc để trống trailer")
    String trailer;
    Integer countryId;
    List<Integer> genreIds;
    List<Integer> actorIds;
    List<Integer> directorIds;
}
