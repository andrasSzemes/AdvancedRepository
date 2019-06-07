package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.ProductEntity;
import com.codecool.advanced_project.repository.ProductRepository;
import com.codecool.advanced_project.service.ProductServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerJPA {

    @Autowired
    private ProductServiceJPA productServiceJPA;

    @GetMapping("/{id}")
    public ProductEntity findProduct(@PathVariable("id") Long id) {
        return this.productServiceJPA.findById(id);
    }

    @GetMapping("/{name}")
    public ProductEntity findProductByName(@PathVariable("name") String name) {
        return this.productServiceJPA.findByName(name);
    }

    @PostMapping("")
    public ProductEntity addProduct(@RequestBody @Valid ProductEntity newProduct) {
        this.productServiceJPA.add(newProduct);
        return newProduct;
    }

    @GetMapping("")
    public List<ProductEntity> listProducts() {
        return this.productServiceJPA.getAll();
    }

    @PutMapping("")
public void updateProduct(HttpServletResponse response, @RequestBody @Valid ProductEntity productEntity) throws IOException {
        if (productServiceJPA.updateProduct(productEntity) == 0)
            response.sendError(500, "Product not found");
    }
}
