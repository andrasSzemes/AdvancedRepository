package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.ShopEntity;
import com.codecool.advanced_project.service.ShopServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopControllerJPA {

    @Autowired
    ShopServiceJPA shopServiceJPA;

    @GetMapping("/all")
    public List<ShopEntity> getUsersShops() {
        return shopServiceJPA.getShops();
    }
}
