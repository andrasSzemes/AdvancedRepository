package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.LineItemEntity;
import com.codecool.advanced_project.entity.ShoppingListEntity;
import com.codecool.advanced_project.repository.LineItemRepository;
import com.codecool.advanced_project.service.ShoppingListServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-list")
@CrossOrigin
public class ShoppingListControllerJPA {

    @Autowired
    private ShoppingListServiceJPA shoppingListServiceJPA;

    @Autowired
    private LineItemRepository lineItemRepository;

    @GetMapping("/latest/{userId}")
    public ShoppingListEntity getUsersLatest(@PathVariable String userId) {
        return shoppingListServiceJPA.getLatest(Long.parseLong(userId));
    }

    @PostMapping("/new")
    public String addShoppingList(@RequestBody ShoppingListEntity shoppingList) {
        shoppingListServiceJPA.saveNew(shoppingList);
        return "Successful";
    }

    @PostMapping("/add")
    public String addLineItem(@RequestBody LineItemEntity lineItem) {
        lineItemRepository.save(lineItem);
        return "Successful";
    }

}
