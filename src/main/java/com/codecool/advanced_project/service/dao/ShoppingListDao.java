package com.codecool.advanced_project.service.dao;

import com.codecool.advanced_project.model.ShoppingList;

public interface ShoppingListDao {
    public ShoppingList getLatest(int userId);
}
