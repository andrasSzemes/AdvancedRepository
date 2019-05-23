package com.codecool.advanced_project.model.mapper;

import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.dao.ProductCategoryDao;
import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    private ProductCategoryDao productCategoryDao;

    @Autowired
    public ProductRowMapper(ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        if (resultSet.getInt("category_tag_id") > 0) {
            ProductCategory category = productCategoryDao.findById(resultSet.getInt("category_tag_id"));
            product.setProductCategory(category);
        }
        product.setPicture(resultSet.getString("picture_url"));
        return product;
    }
}
