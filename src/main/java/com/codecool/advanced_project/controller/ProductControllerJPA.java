package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.LineItemEntity;
import com.codecool.advanced_project.entity.ProductEntity;
import com.codecool.advanced_project.service.ProductServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductControllerJPA {

    @Autowired
    private ProductServiceJPA productServiceJPA;

    @GetMapping("/{id}")
    public ResponseEntity findProduct(@PathVariable("id") Long id) {
        if (this.productServiceJPA.findById(id).isPresent()) {
            return ResponseEntity.ok(this.productServiceJPA.findById(id));
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{name}")
    public ResponseEntity findProductByName(@PathVariable("name") String name) {
        if (this.productServiceJPA.findByName(name).isPresent()) {
            return ResponseEntity.ok(this.productServiceJPA.findByName(name));
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity addProduct(@RequestBody @Valid ProductEntity newProduct) {
        return ResponseEntity.ok(this.productServiceJPA.add(newProduct));
    }

    @GetMapping("")
    public ResponseEntity listProducts() {
        List<ProductEntity> all = this.productServiceJPA.getAll();
        for (ProductEntity entity : all) {
            LineItemEntity lineItemEntity = entity.getLineItemEntity();
            lineItemEntity.setProduct(null);
            lineItemEntity.setShoppingList(null);
        }
        return ResponseEntity.ok(this.productServiceJPA.getAll());
    }

    @PutMapping("")
    public ResponseEntity updateProduct(@RequestBody @Valid ProductEntity productEntity) {
        if (this.productServiceJPA.findById(productEntity.getId()).isPresent()) {
            this.productServiceJPA.updateProduct(productEntity);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
}
