package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.response.PageResponse;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void printListPeople(List<Product> products);

    List<Product> getAll(); //Liệt kê danh sách tất cả

    PageResponse<Product> getAllProduct(int page, int pageSize);

    Product getProductById(int id);

    List<Product> getProductByName(String keyword);

}
