package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.*;
import com.codecool.advanced_project.model.mapper.ShoppingListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShoppingListController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/latest/{userId}")
    public ShoppingList getUsersLatest(@PathVariable String userId) {
        //todo get users group ids
        String query = "SELECT group_id FROM member_groups WHERE member_id=?";
        List<Integer> groupIds = jdbcTemplate.queryForObject(query, new Object[] {Integer.parseInt(userId)}, ArrayList.class);

        //todo query where group id or user id matches
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM shopping_list WHERE member_id=?");
        for (int i = 0; i < groupIds.size(); i++) {
            if(i == 0) {
                queryBuilder.append(" AND (group_id=?");
            } else {
                queryBuilder.append(" OR group_id=?");
            }
            if(i == groupIds.size() - 1) {
                queryBuilder.append(")");
            }
        }
        queryBuilder.append(" ORDER BY id DESC LIMIT 1;");
        query = queryBuilder.toString();
        List<Integer> queryParams = new ArrayList<>();
        queryParams.add(Integer.parseInt(userId));
        queryParams.addAll(groupIds);
        ShoppingList shoppingList;
        try {
            shoppingList = (ShoppingList) jdbcTemplate.queryForObject(query, queryParams.toArray(), new ShoppingListRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        //todo query to get line items by list id
        List<LineItem> lineItems = new ArrayList<>();
        query = "SELECT * FROM line_item LEFT JOIN list_line_items ON(line_item.id = list_line_items.line_item_id) WHERE list_line_items.shopping_list_id=" + shoppingList.getId();
        List<Map<String, Object>> queryRows = jdbcTemplate.queryForList(query);
        for (Map<String, Object> queryRow : queryRows) {
            LineItem lineItem = new LineItem();
            lineItem.setId((Integer) queryRow.get("id"));
            lineItem.setQuantity((String) queryRow.get("quantity"));
            lineItem.setArchived((Boolean) queryRow.get("archived"));
            lineItem.setProductId((Integer) queryRow.get("product_id"));
            lineItems.add(lineItem);
        }
        shoppingList.setLineItems(lineItems);

        return shoppingList;
    }
}
