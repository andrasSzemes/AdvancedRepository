package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.LineItemEntity;
import com.codecool.advanced_project.entity.ShoppingListEntity;
import com.codecool.advanced_project.repository.LineItemRepository;
import com.codecool.advanced_project.service.ShoppingListServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shopping-lists")
@CrossOrigin
public class ShoppingListControllerJPA {

    @Autowired
    private ShoppingListServiceJPA shoppingListServiceJPA;

    @Autowired
    private LineItemRepository lineItemRepository;

    @GetMapping("/latest/{userId}")
    public ShoppingListEntity getUsersLatest(@PathVariable String userId) {
        return shoppingListServiceJPA.getLatest(Long.parseLong(userId));
    }

    @GetMapping("/latest-by-group/{groupId}")
    public ResponseEntity getGroupsLatest(HttpServletResponse response, @PathVariable String groupId) throws IOException {
        //Todo: check if request is valid or not
        ShoppingListEntity list = shoppingListServiceJPA.getLatestByGroup(Long.parseLong(groupId));
        if (list == null) response.sendError(404, "No shopping list found for id: " + groupId);

        Map<Object, Object> model = new HashMap<>();
        model.put("shoppingList", list);
        return ResponseEntity.ok(model);
    }

    @PostMapping("")
    @ResponseBody
    public String addShoppingList(@RequestBody ShoppingListEntity shoppingList) {
        shoppingListServiceJPA.saveNew(shoppingList);
        return "Successful";
    }

    @PostMapping("/add")
    public String addLineItem(@RequestBody LineItemEntity lineItem) {
        lineItemRepository.save(lineItem);
        return "Successful";
    }

}
