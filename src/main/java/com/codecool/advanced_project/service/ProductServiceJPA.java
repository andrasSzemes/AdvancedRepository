package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.ProductEntity;
import com.codecool.advanced_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceJPA {

    @Autowired
    ProductRepository productRepository;

    public ProductEntity findById(Integer id) {
        return productRepository.find(id);
    }

    public void add(ProductEntity newProduct) {
        productRepository.save(newProduct);
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public ProductEntity findByName(String name) { return productRepository.findByName(name);
    }
}
