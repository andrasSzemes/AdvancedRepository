package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.ShoppingList;
import com.codecool.advanced_project.service.ShoppingListCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-list")
public class ShoppingListController {
    private ShoppingListCreator shoppingListCreator;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShoppingListController(ShoppingListCreator shoppingListCreator, JdbcTemplate jdbcTemplate) {
        this.shoppingListCreator = shoppingListCreator;
        this.jdbcTemplate = jdbcTemplate;
    }



    @GetMapping("/latest/{userId}")
    public ShoppingList getUsersLatest(@PathVariable String userId) {
        //todo create getUsersLatest
        return null;
    }
}
