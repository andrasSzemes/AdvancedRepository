package com.codecool.advanced_project.dao.implementation;

import com.codecool.advanced_project.dao.ProductDao;
import com.codecool.advanced_project.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoDB implements ProductDao {
    private List<Product> data = new ArrayList<>();
    private static ProductDaoDB instance = null;

    private ProductDaoDB() {
    }

    public static ProductDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void removeAll() {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(String category) {
        return null;
    }
}
