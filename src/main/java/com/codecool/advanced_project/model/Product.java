package com.codecool.advanced_project.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private ProductCategory category;
    private List<String> tags = new ArrayList<>();

    public Product() {

    }

    public Product(String name, ProductCategory category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
