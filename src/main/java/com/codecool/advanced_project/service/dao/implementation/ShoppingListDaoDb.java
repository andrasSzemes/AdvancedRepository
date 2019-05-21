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
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class ShoppingListDaoDb implements ShoppingListDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Function<Integer, List<LineItem>> getAllLineItem;
    @Autowired
    private Function<Integer, List<Integer>> getGroupIds;

    @Override
    public ShoppingList getLatest(int userId) {

        List<Integer> groupIds = getGroupIds.apply(userId);
        List<Integer> queryParams = getQueryParams(userId, groupIds);

        String query = buildQuery(groupIds.size());
        ShoppingList shoppingList = executeQuery(query, queryParams.toArray());

        if (shoppingList != null) {
            List<LineItem> lineItems = getAllLineItem.apply(shoppingList.getId());
            shoppingList.setLineItems(lineItems);
        }

        return shoppingList;
    }

    private ShoppingList executeQuery(String query, Object[] params) {
        ShoppingList shoppingList;
        try {
            shoppingList = (ShoppingList) jdbcTemplate.queryForObject(query, params, new ShoppingListRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return shoppingList;
    }

    private List<Integer> getQueryParams(int userId, List<Integer> groupIds) {
        List<Integer> queryParams = new ArrayList<>();
        queryParams.add(userId);
        queryParams.addAll(groupIds);
        return queryParams;
    }

    private String buildQuery(int numOfGroupIds) {
        String query;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM shopping_list WHERE member_id=?");
        for (int i = 0; i < numOfGroupIds; i++) {
            queryBuilder.append(" OR group_id=?");
            if (i == numOfGroupIds - 1) {
                queryBuilder.append(")");
            }
        }
        queryBuilder.append(" ORDER BY id DESC LIMIT 1;");
        query = queryBuilder.toString();
        return query;
    }
}
