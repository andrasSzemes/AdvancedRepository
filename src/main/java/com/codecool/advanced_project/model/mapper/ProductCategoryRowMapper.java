package com.codecool.advanced_project.model.mapper;


import com.codecool.advanced_project.model.ProductCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryRowMapper implements RowMapper<ProductCategory> {

    @Override
    public ProductCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(resultSet.getInt("id"));
        productCategory.setName(resultSet.getString("name"));
        return productCategory;
    }
}
