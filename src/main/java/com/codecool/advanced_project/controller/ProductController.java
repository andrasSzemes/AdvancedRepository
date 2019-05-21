package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("/find/{id}")
    public Product findProduct(@PathVariable("id") int id) throws Exception {
        return productService.findById(id);
    }
}
