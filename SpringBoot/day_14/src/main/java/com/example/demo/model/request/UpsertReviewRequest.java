package com.example.demo.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpsertReviewRequest {
    @NotEmpty(message = "Không được để nội dung trống")
    String content;
    @NotNull(message = "Không được để rating trống")
    @Min(value = 1, message = "Rating phải lớn hơn 0")
    Integer rating;
    Integer movieId;
}
