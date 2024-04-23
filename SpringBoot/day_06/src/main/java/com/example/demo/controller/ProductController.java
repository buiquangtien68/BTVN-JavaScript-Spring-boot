package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String product(Model model, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "16") int pageSize) {
        model.addAttribute("pageData", productService.getAllProduct(page, pageSize));
        return "product";
    }

    @GetMapping("/products/{id}")
    public String product(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-detail";
    }


}
