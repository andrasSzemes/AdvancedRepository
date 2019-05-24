package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.ListItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tolists")
@CrossOrigin
public class ListController {

    @PostMapping("")
    public ResponseEntity<ListItem> test(@RequestBody ListItem l){

        System.out.println(l);

        return new ResponseEntity<>(l, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public String test2(){
        return "asdf";
    }


}
