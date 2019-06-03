package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.ProductCategoryEntity;
import com.codecool.advanced_project.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCategoryServiceJPA {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public ProductCategoryEntity findById(Long id) {
        return productCategoryRepository.find(id);
    }

    public void add(ProductCategoryEntity newProduct) {
        productCategoryRepository.save(newProduct);
    }

    public List<ProductCategoryEntity> getAll() {
        return productCategoryRepository.findAll();
    }

    public ProductCategoryEntity findByName(String name) { return productCategoryRepository.findByName(name);
    }
}
