package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {

    ShoppingListEntity find(Long id);

    ShoppingListEntity findByName(String name);

    List<ShoppingListEntity> findAllByCategory(ShoppingListEntity productCategory);

    List<ShoppingListEntity> findAll();

    void deleteById(Long id);

    void deleteAll();

    List<ShoppingListEntity> getAll();

    @Query("select name from shopping_list where id = (select max(id) from shopping_list )")
    //TODO check if all methods are migrated
    ShoppingListEntity getLatest();
}
