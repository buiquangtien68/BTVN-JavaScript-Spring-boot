package com.example.demo.utils;

import com.example.demo.model.Product;

import java.io.IOException;
import java.util.List;

public interface IReadFile {
    List<Product> readFile(String path) throws IOException;
}
