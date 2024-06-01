package com.example.demo.model.dataChart;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserData {
    Integer month;
    Integer year;
    Integer userCount;

}
