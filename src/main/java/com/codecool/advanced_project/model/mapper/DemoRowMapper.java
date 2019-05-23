package com.codecool.advanced_project.model.mapper;

import com.codecool.advanced_project.model.DemoProduct;
import com.codecool.advanced_project.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoRowMapper implements RowMapper<DemoProduct> {
    @Override
    public DemoProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        return new DemoProduct(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getBoolean("completed"));
    }
}
