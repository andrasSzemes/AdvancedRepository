package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository  extends JpaRepository<ProductCategoryEntity, Long> {

    Optional<ProductCategoryEntity> findById(Long id);

    ProductCategoryEntity findByName(String name);

    void deleteById(Long id);

    void deleteAll();

    List<ProductCategoryEntity> findAll();
}
