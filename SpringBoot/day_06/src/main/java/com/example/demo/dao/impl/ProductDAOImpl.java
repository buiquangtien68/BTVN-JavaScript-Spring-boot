package com.example.demo.dao.impl;

import com.example.demo.dao.ProductDAO;

import com.example.demo.database.ProductDB;
import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> getAll() {
        return ProductDB.products;
    }

    @Override
    public Product getById(int id) {
        return ProductDB.products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Product> getByName(String keyword) {
        return ProductDB.products.stream().filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase())).toList();
    }
}
