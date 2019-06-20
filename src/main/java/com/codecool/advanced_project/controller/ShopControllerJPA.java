package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.ShopEntity;
import com.codecool.advanced_project.service.ShopServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/shops")
@CrossOrigin
public class ShopControllerJPA {

    @Autowired
    ShopServiceJPA shopServiceJPA;

    @GetMapping("")
    public List<ShopEntity> getUsersShops() {
        return shopServiceJPA.getShops();
    }

    @PostMapping("")
    public ShopEntity addNewShop(@RequestBody @Valid ShopEntity shopEntity) {
        return shopServiceJPA.addShop(shopEntity);
    }

    @PutMapping("/edit")
    public void updateShop(HttpServletResponse response, @RequestBody @Valid ShopEntity shopEntity) throws IOException {
        //Todo: ask about why 500 when not found error
        if(shopServiceJPA.updateShop(shopEntity) == 0) response.sendError(500, "Shop not found");
    }

}
