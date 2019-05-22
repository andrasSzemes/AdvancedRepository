package com.codecool.advanced_project.dao;

import com.codecool.advanced_project.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class ShoppingListDao {

    private DataSource dataSource;

    @Autowired
    public ShoppingListDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveNewShoppingList(ShoppingList shoppingList) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO shopping_list" +
                     "(associated_shop_id, archived, member_id, group_id)" +
                     "VALUES (-1,FALSE,-1,?)";

        jdbcTemplate.update(sql, shoppingList.getGroupId());
    }

}
