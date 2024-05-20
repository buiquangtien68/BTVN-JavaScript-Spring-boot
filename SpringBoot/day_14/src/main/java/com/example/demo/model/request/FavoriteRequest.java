package com.example.demo.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class FavoriteRequest {
    @NotNull(message = "không được để trống")
    Integer userId;
    @NotNull(message = "không được để trống")
    Integer movieId;
}
