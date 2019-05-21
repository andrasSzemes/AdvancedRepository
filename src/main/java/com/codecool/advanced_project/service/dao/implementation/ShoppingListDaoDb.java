package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.model.LineItem;
import com.codecool.advanced_project.model.ShoppingList;
import com.codecool.advanced_project.model.mapper.ShoppingListRowMapper;
import com.codecool.advanced_project.service.dao.ShoppingListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ShoppingListDaoDb implements ShoppingListDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShoppingListDaoDb(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ShoppingList getLatest(int userId) {
        //todo get users group ids
        String query = "SELECT group_id FROM member_groups WHERE member_id=?";
        List<Integer> groupIds = new ArrayList<>();
        List<Map<String, Object>> queryRows = jdbcTemplate.queryForList(query, userId);
        for (Map<String, Object> queryRow : queryRows) {
            groupIds.add((Integer) queryRow.get("group_id"));
        }

        //todo query where group id or user id matches
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM shopping_list WHERE member_id=?");
        for (int i = 0; i < groupIds.size(); i++) {
            queryBuilder.append(" OR group_id=?");
            if(i == groupIds.size() - 1) {
                queryBuilder.append(")");
            }
        }
        queryBuilder.append(" ORDER BY id DESC LIMIT 1;");
        query = queryBuilder.toString();
        List<Integer> queryParams = new ArrayList<>();
        queryParams.add(userId);
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
        queryRows = jdbcTemplate.queryForList(query);
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
