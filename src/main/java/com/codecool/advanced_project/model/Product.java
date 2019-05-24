package com.codecool.advanced_project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    private int id;
    private String title;
    private ProductCategory category;
    private String picture;
    private List<String> tags = new ArrayList<>();

    public Product(int id, String title, ProductCategory category, String picture) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.picture = picture;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(String title, ProductCategory category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(title, product.title) &&
                Objects.equals(category, product.category) &&
                Objects.equals(picture, product.picture) &&
                Objects.equals(tags, product.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, category, picture, tags);
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

    public void setProductCategory(ProductCategory category) {
        this.category = category;
    }
}
