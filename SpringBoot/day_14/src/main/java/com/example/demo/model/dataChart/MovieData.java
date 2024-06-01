package com.example.demo.model.dataChart;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MovieData {
    Integer month;
    Integer year;
    Integer movieCount;
}
