package com.codecool.advanced_project.service;

import com.codecool.advanced_project.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopServiceJPA {

    @Autowired
    ShopRepository shopRepository;
}
