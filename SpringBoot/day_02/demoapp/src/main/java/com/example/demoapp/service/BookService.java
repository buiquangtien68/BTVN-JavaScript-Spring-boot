package com.example.demoapp.service;

import com.example.demoapp.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(int id);

    List<Book> sortBooksByYear();

    List<Book> searchBook(String keyword);

    List<Book> searchBookByYear(int startYear, int endYear);
}
