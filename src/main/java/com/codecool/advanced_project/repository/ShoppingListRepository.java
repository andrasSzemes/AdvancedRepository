package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {

    Optional<ShoppingListEntity> findById(Long id);

    List<ShoppingListEntity> findAll();

    void deleteById(Long id);

    void deleteAll();

    ShoppingListEntity findFirstByMemberIdEqualsOrderById(Long userID);
}
