package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.ShoppingListEntity;
import com.codecool.advanced_project.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ShoppingListServiceJPA {

    @Autowired
    ShoppingListRepository shoppingListRepository;

    public ShoppingListEntity getLatest(Long userId) {
        return shoppingListRepository.findFirstByMemberIdEqualsOrderById(userId);
    }

    public ShoppingListEntity findById(Long id) {
        Optional<ShoppingListEntity> shoppingListEntity = shoppingListRepository.findById(id);
        if(shoppingListEntity.isPresent()) return shoppingListEntity.get();
        return null;
    }

    public void saveNew(ShoppingListEntity newShoppingList) {
        shoppingListRepository.save(newShoppingList);
    }

    public ShoppingListEntity getAll() {
        return shoppingListRepository.findAll().get(0);
    }
}
