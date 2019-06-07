package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.ProductEntity;
import com.codecool.advanced_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceJPA {

    @Autowired
    ProductRepository productRepository;

    public ProductEntity findById(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) return productEntity.get();
        return null;
    }

    public ProductEntity add(ProductEntity newProduct) {
        return productRepository.save(newProduct);
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public ProductEntity findByName(String name) {
        return productRepository.findByName(name);
    }

    public int updateProduct(ProductEntity productEntity) {
        return productRepository.updateProduct(productEntity, productEntity.getId());
    }
}
