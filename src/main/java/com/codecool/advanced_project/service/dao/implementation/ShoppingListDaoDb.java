package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.model.ShoppingList;
import com.codecool.advanced_project.service.ShoppingListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ShoppingListDaoDb implements ShoppingListDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShoppingListDaoDb(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveNew(ShoppingList shoppingList) {
        String sql = "INSERT INTO shopping_list" +
                     "(associated_shop_id, archived, member_id, group_id)" +
                     "VALUES (-1,FALSE,-1,?)";

        jdbcTemplate.update(sql, shoppingList.getGroupId());
    }

}
