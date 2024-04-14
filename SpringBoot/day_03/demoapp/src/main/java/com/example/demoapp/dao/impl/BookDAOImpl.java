package com.example.demoapp.dao.impl;

import com.example.demoapp.dao.BookDAO;
import com.example.demoapp.database.BookDB;
import com.example.demoapp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class BookDAOImpl  implements BookDAO {

    @Override
    public List<Book> findAll() {
        return BookDB.books;
    }

    @Override
    public Book findById(int id) {
      return BookDB.books.stream().filter(book -> book.getId()==id).findFirst().orElse(null);
    }

    @Override
    public List<Book> sortBookByYear() {
        return BookDB.books.stream().sorted((a,b) ->a.getYear()- b.getYear()).toList();
    }

    @Override
    public List<Book> searchBook(String keyword) {
        return BookDB.books.stream().filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase())).toList();
    }

    @Override
    public List<Book> searchBookByYear(int startYear, int endYear) {
        return BookDB.books.stream().filter(book -> book.getYear()>=startYear && book.getYear()<=endYear).toList();
    }
}
