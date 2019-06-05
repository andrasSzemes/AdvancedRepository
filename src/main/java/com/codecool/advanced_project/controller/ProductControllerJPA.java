package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.ProductEntity;
import com.codecool.advanced_project.service.ProductServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductControllerJPA {

    @Autowired
    private ProductServiceJPA productServiceJPA;

    @GetMapping("/find/{id}")
    public ProductEntity findProduct(@PathVariable("id") Long id) {
        return this.productServiceJPA.findById(id);
    }

    @GetMapping("/find/{name}")
    public ProductEntity findProductByName(@PathVariable("name") String name) {
        return this.productServiceJPA.findByName(name);
    }

    @PostMapping("/add")
    public ProductEntity addProduct(@RequestBody @Valid ProductEntity newProduct) {
        return this.productServiceJPA.add(newProduct);
    }

    @GetMapping("/list/all")
    public List<ProductEntity> listProducts() {
        return this.productServiceJPA.getAll();
    }
}
