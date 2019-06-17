package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {

    Optional<ProductCategoryEntity> findById(Long id);

    ProductCategoryEntity findByName(String name);

    void deleteById(Long id);

    void deleteAll();

    List<ProductCategoryEntity> findAll();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE ProductCategoryEntity product_category set product_category.name = :#{#entity.name} where product_category.id = :id")
    int updateProductCategory(@Param("entity") ProductCategoryEntity productCategoryEntity, @Param("id") Long id);
}
