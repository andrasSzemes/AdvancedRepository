package com.codecool.advanced_project.dao;

import com.codecool.advanced_project.model.Product;

import java.util.List;

public interface ProductDao {

    void add(Product product);

    Product find(int id);

    void remove(int id);

    void removeAll();

    List<Product> getAll();

    List<Product> getBy(String category);

    void addPicture(int id, String url);

}
