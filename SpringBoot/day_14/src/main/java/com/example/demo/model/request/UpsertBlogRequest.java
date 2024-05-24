package com.example.demo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpsertBlogRequest {
    @NotBlank(message = "K đc để trống title")
    String title;
    @NotBlank(message = "K đc để trống content")
    String content;
    @NotBlank(message = "K đc để trống description")
    String description;

    Boolean status;
}
