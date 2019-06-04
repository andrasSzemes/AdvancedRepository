package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ProductCategoryEntity;
import com.codecool.advanced_project.entity.ShoppingListEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {

    Optional<ShoppingListEntity> findById(Long id);

    List<ShoppingListEntity> findAll();

    void deleteById(Long id);

    void deleteAll();

    ShoppingListEntity findFirstByMemberIdEqualsOrderById(Long userID);
}
