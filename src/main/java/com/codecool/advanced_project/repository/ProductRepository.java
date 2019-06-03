package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ProductCategoryEntity;
import com.codecool.advanced_project.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(Long id);

    ProductEntity findByName(String name);

    List<ProductEntity> findAll();

    void deleteById(Long id);

    void deleteAll();


}
