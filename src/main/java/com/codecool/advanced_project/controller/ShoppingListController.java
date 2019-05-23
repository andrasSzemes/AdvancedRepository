package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.ShoppingList;
import com.codecool.advanced_project.service.dao.ShoppingListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private ShoppingListDao shoppingListDao;

    @Autowired
    public ShoppingListController(ShoppingListDao ShoppingListDaoInUse) {
        this.shoppingListDao = ShoppingListDaoInUse;
    }

    @GetMapping("/latest/{userId}")
    public ShoppingList getUsersLatest(@PathVariable String userId) {
        return shoppingListDao.getLatest(Integer.parseInt(userId));
    }
}
