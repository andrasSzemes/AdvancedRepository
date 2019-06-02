package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.ShoppingListEntity;
import com.codecool.advanced_project.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShoppingListServiceJPA {

    @Autowired
    ShoppingListRepository shoppingListRepository;

    public ShoppingListEntity getLatest(Integer userId) {
        return shoppingListRepository.getLatest();
    }

    public ShoppingListEntity findById(Integer id) {
        return shoppingListRepository.find(id);
    }

    public void saveNew(ShoppingListEntity newShoppingList) {
        shoppingListRepository.save(newShoppingList);
    }

    public List<ShoppingListEntity> getAll() {
        return shoppingListRepository.findAll();
    }

    public ShoppingListEntity findByName(String name) {
        return shoppingListRepository.findByName(name);
    }
}
