package com.codecool.advanced_project.service.dao;

import com.codecool.advanced_project.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category);
    ProductCategory findByName(String name);
    void remove(int id);

    List<ProductCategory> getAll();
    void removeAll();

    Integer getId(String name);

    ProductCategory findById(int id);
}
