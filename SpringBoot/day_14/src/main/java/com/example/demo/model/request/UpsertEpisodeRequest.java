package com.example.demo.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpsertEpisodeRequest {
    @NotEmpty(message = "K được để trống tên tập phim")
    String name;
    @NotNull(message = "K được để trống thứ tự tập phim")
    Integer orders;
    Integer movieId;
}
