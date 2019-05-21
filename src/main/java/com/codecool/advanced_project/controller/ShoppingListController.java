package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.*;
import com.codecool.advanced_project.model.mapper.ShoppingListRowMapper;
import com.codecool.advanced_project.service.dao.ShoppingListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private ShoppingListDao shoppingListDao;

    @Autowired
    public ShoppingListController(ShoppingListDao ShoppingListDaoDb) {
        this.shoppingListDao = ShoppingListDaoDb;
    }

    @GetMapping("/latest/{userId}")
    public ShoppingList getUsersLatest(@PathVariable String userId) {
        return shoppingListDao.getLatest(Integer.parseInt(userId));
    }
}
