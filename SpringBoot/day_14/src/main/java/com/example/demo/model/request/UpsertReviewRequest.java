package com.example.demo.model.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpsertReviewRequest {
    String content;
    Integer rating;
    Integer movieId;
}
