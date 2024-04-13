package com.example.demoapp.database;

import com.example.demoapp.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDB {
    public static List<Book> books = new ArrayList<>(
            List.of(
                    new Book(1, "Harry Potter", "J.K. Rowling", 1997),
                    new Book(2, "The Lord of the Rings", "J.R.R. Tolkien", 1954),
                    new Book(3, "The Da Vinci Code", "Dan Brown", 2003)
            )
    );

}
