package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.ShopEntity;
import com.codecool.advanced_project.service.ShopServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/add")
    public ShopEntity addNewShop(@RequestBody @Valid ShopEntity shopEntity) {
        shopServiceJPA.addShop(shopEntity);
        return shopEntity;
    }

}
