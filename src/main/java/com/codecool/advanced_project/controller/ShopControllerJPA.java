package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopControllerJPA {

    @Autowired
    ShopRepository shopRepository;
}
