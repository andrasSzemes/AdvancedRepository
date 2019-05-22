package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.dao.ShoppingListDao;
import com.codecool.advanced_project.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private ShoppingListDao shoppingListDao;

    @Autowired
    ShoppingListController(ShoppingListDao shoppingListDao) {
        this.shoppingListDao = shoppingListDao;
    }

    @PostMapping("/add")
    public void addShoppingList(@RequestBody ShoppingList shoppingList) {
        shoppingListDao.saveNewShoppingList(shoppingList);
    }

}
