package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.model.mapper.ProductRowMapper;
import com.codecool.advanced_project.service.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductDaoDb implements ProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM product WHERE id=?";
        try {
            return (Product) jdbcTemplate.queryForObject(query, new Object[]{id}, new ProductRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
