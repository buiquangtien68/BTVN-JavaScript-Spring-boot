package com.example.demoapp.model;

import lombok.Generated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
     int id;
     String title;
     String author;
     int year;
}
