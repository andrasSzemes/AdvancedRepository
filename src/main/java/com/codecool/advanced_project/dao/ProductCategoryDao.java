package com.codecool.advanced_project.dao;

import com.codecool.advanced_project.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category);
    ProductCategory find(int id);
    void remove(int id);

    List<ProductCategory> getAll();
    void removeAll();

    Integer getId(String name);

}
