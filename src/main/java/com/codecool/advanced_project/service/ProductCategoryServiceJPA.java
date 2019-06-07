package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.ProductCategoryEntity;
import com.codecool.advanced_project.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductCategoryServiceJPA {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public ProductCategoryEntity findById(Long id) {
        Optional<ProductCategoryEntity> productCategoryEntity = productCategoryRepository.findById(id);
        if (productCategoryEntity.isPresent()) return productCategoryEntity.get();
        return null;
    }

    public void add(ProductCategoryEntity newProduct) {
        productCategoryRepository.save(newProduct);
    }

    public List<ProductCategoryEntity> getAll() {
        return productCategoryRepository.findAll();
    }

    public ProductCategoryEntity findByName(String name) {
        return productCategoryRepository.findByName(name);
    }

    public int updateProductCategory(ProductCategoryEntity productCategoryEntity) {
        return productCategoryRepository.updateProductCategory(productCategoryEntity, productCategoryEntity.getId());
    }
}
