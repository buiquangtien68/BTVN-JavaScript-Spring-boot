package com.example.demo.service.impl;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
import com.example.demo.response.PageResponse;
import com.example.demo.response.impl.PageResponseImpl;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;
    @Override
    public void printListPeople(List<Product> products) {

    }

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public PageResponse<Product> getAllProduct(int page, int pageSize) {
        return new PageResponseImpl<>(productDAO.getAll(), page, pageSize);
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getById(id);
    }

    @Override
    public List<Product> getProductByName(String keyword) {
        return productDAO.getByName(keyword);
    }

}
