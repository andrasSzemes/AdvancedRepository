package com.codecool.advanced_project.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    private String name;
    private List<Product> products = new ArrayList<>();

    public ProductCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

}