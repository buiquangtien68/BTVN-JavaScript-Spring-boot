package com.example.demo.dao;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductDAO {
    List<Product> getAll(); //Liệt kê danh sách tất cả

    Product getById(int id);

    List<Product> getByName(String keyword);
}
