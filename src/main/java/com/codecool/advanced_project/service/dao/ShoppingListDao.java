package com.codecool.advanced_project.service.dao;

import com.codecool.advanced_project.model.ShoppingList;

public interface ShoppingListDao {
    ShoppingList getLatest(int userId);
    void saveNew(ShoppingList shoppingList);
}
