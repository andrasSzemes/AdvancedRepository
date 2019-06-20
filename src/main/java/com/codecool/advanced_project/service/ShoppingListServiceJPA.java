package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.LineItemEntity;
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
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findFirstByMemberIdEqualsOrderById(userId);
//        ShoppingListEntity shoppingListEntity = shoppingListRepository.findById(201L).get();
        for (LineItemEntity lineItem : shoppingListEntity.getLineItems()) {
            lineItem.getProduct().setLineItemEntity(null);
            lineItem.setShoppingList(null);
        }

        return shoppingListEntity;
    }

    public ShoppingListEntity getLatestByGroup(Long groupId) {
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findFirstByGroupIdOrderById(groupId);
        if (shoppingListEntity == null) return null;
        for (LineItemEntity lineItem : shoppingListEntity.getLineItems()) {
            lineItem.getProduct().setLineItemEntity(null);
            lineItem.setShoppingList(null);
        }

        return shoppingListEntity;
    }

    public ShoppingListEntity findById(Long id) {
        Optional<ShoppingListEntity> shoppingListEntity = shoppingListRepository.findById(id);
        if(shoppingListEntity.isPresent()) return shoppingListEntity.get();
        return null;
    }

    public void saveNew(ShoppingListEntity newShoppingList) {
        shoppingListRepository.save(newShoppingList);
    }
}
