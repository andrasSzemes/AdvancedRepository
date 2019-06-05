package com.codecool.advanced_project.service;

import com.codecool.advanced_project.entity.ShopEntity;
import com.codecool.advanced_project.exception.NoOpeningHoursException;
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
        List<ShopEntity> shops = shopRepository.findAll();
        for (ShopEntity shop : shops) {
            try{
                shop.setIsOpen();
                //todo possible filter point for opening hours
            } catch (NoOpeningHoursException e) {
                shop.setOpen(null);
            }
        }
        return shops;
    }
}
