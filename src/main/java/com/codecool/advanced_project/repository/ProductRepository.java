package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    Optional<ProductEntity> findById(Long id);

    Optional<ProductEntity> findByName(String name);

    List<ProductEntity> findAll();

    void deleteById(Long id);

    void deleteAll();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE ProductEntity product set product.name = :#{#entity.name}, product.category = :#{#entity.category} where product.id = :id")
    int updateProduct(@Param("entity") ProductEntity productEntity, @Param("id") Long id);
}


