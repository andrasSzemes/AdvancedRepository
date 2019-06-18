package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.LineItemEntity;
import com.codecool.advanced_project.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/line-item")
@CrossOrigin
public class LineItemControllerJPA {

    @Autowired
    LineItemRepository lineItemRepository;

    @PutMapping("/check")
    public String checkLineItem(@RequestBody LineItemEntity lineItem) {
        lineItemRepository.updateArchivedState(lineItem.getId(), ! lineItem.getIsArchived());
        return "Successful";
    }
}
