package com.example.demoapp.dao;

import com.example.demoapp.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();

    Book findById(int id);

    List<Book> sortBookByYear();

    List<Book> searchBook(String keyword);

    List<Book> searchBookByYear(int startYear, int endYear);
}
