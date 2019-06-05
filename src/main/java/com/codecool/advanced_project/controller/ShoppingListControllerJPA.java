package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.ShoppingListEntity;
import com.codecool.advanced_project.service.ShoppingListServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-list")
@CrossOrigin
public class ShoppingListControllerJPA {

    @Autowired
    private ShoppingListServiceJPA shoppingListServiceJPA;

    @GetMapping("/latest/{userId}")
    public ShoppingListEntity getUsersLatest(@PathVariable String userId) {
        return shoppingListServiceJPA.getLatest(Long.parseLong(userId));
    }

    @GetMapping("/all")
    public ShoppingListEntity getAll() {
        return shoppingListServiceJPA.getAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public String addShoppingList(@RequestBody ShoppingListEntity shoppingList) {
        shoppingListServiceJPA.saveNew(shoppingList);
        return "Successful";
    }

}
