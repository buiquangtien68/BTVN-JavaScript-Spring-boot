package com.example.demoapp.utils;

import com.example.demoapp.model.Book;

import java.io.IOException;
import java.util.List;

public interface IFileReader {
    List<Book> readFile(String path) throws IOException;
}
