package com.codecool.advanced_project.model.mapper;

import com.codecool.advanced_project.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setProductCategoryTagId(resultSet.getInt("category_tag_id"));
        product.setPicture(resultSet.getString("picture_url"));
        return product;
    }
}
