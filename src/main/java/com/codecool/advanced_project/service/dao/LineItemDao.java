package com.codecool.advanced_project.service.dao;

import com.codecool.advanced_project.model.LineItem;

import java.util.List;

public interface LineItemDao {
    List<LineItem> getAll(int shoppingListId);
}
