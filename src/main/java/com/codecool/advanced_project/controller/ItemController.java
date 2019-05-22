package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.ShopItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class ItemController {

    @PostMapping("")
    public ResponseEntity<ShopItem> test(@RequestBody ShopItem s){

        System.out.println(s);

        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public String test2(){
        return "asdf";
    }


}
