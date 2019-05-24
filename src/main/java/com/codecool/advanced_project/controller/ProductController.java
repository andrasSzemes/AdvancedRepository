package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class    ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/find/{id}")
    public Product findProduct(@PathVariable("id") int id) {
        return this.productService.findById(id);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody @Valid Product newProduct) {
        this.productService.add(newProduct);
        return newProduct;
    }

    @GetMapping("/list/all")
    public List<Product> listProducts() {
        return this.productService.getAll();
    }
}
