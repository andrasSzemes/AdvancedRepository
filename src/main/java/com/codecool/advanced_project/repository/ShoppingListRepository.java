package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Integer> {

    ShoppingListEntity find(Integer id);

    ShoppingListEntity findByName(String name);

    List<ShoppingListEntity> findAllByCategory(ShoppingListEntity productCategory);

    List<ShoppingListEntity> findAll();

    void removeById(Integer id);

    void removeAllBy();

    List<ShoppingListEntity> getAllBy();

    @Query("select name from shopping_list where id = (select max(id) from shopping_list )")
    //TODO
    ShoppingListEntity getLatest();

}
