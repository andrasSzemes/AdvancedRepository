package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.LineItemEntity;
import com.codecool.advanced_project.repository.LineItemRepository;
import com.codecool.advanced_project.repository.ProductCategoryRepository;
import com.codecool.advanced_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/line-item")
@CrossOrigin
public class LineItemControllerJPA {

    @Autowired
    LineItemRepository lineItemRepository;
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
    public LineItemEntity saveLineItem(@RequestBody @Valid LineItemEntity lineItemEntity) {
        productRepository.save(lineItemEntity.getProduct());
        return lineItemRepository.save(lineItemEntity);
    }

}
