package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ProductCategoryEntity;
import com.codecool.advanced_project.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    ProductEntity find(Integer id);

    ProductEntity findByName(String name);

    List<ProductEntity> findAllByCategory(ProductCategoryEntity productCategory);

    List<ProductEntity> findAll();

    void removeById(Long id);

    void removeAllBy();

    List<ProductEntity> getAllBy();


}
