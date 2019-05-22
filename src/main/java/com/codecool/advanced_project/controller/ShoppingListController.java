package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.service.dao.implementation.ShoppingListDaoDb;
import com.codecool.advanced_project.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private ShoppingListDaoDb shoppingListDao;

    @Autowired
    ShoppingListController(ShoppingListDaoDb shoppingListDao) {
        this.shoppingListDao = shoppingListDao;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addShoppingList(@RequestBody ShoppingList shoppingList) {
        shoppingListDao.saveNew(shoppingList);
        return "Successful";
    }

}
