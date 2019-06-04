package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.ShopEntity;
import com.codecool.advanced_project.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopServiceJPA {

    @Autowired
    ShopRepository shopRepository;

    public List<ShopEntity> getShops() {
        return shopRepository.findAll();
    }
}
