package com.codecool.advanced_project.model.mapper;

import com.codecool.advanced_project.model.ShoppingList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingListRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(resultSet.getInt("id"));
        shoppingList.setAssociatedShopId(resultSet.getInt("associated_shop_id"));
        shoppingList.setArchived(resultSet.getBoolean("archived"));
        shoppingList.setMemberId(resultSet.getInt("member_id"));
        shoppingList.setGroupId(resultSet.getInt("group_id"));
        return shoppingList;
    }
}
