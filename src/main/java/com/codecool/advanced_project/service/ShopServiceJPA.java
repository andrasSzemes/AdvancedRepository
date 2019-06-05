package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.ShopEntity;
import com.codecool.advanced_project.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Component
public class ShopServiceJPA {

    @Autowired
    ShopRepository shopRepository;

    public List<ShopEntity> getShops() {
        return shopRepository.findAll();
    }


    @PostMapping("/add")
    public ShopEntity addShop(@RequestBody @Valid ShopEntity newShop) {
        return this.shopRepository.save(newShop);
    }

}
