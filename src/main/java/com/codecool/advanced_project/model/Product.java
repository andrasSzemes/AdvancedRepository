package com.codecool.advanced_project.model;

public class Product {
    private int id;
    private String name;
    private int productCategoryTagId;
    private String picture;

    public Product(int id, String name, int productCategoryTagId, String picture) {
        this.id = id;
        this.name = name;
        this.productCategoryTagId = productCategoryTagId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCategoryTagId() {
        return productCategoryTagId;
    }

    public void setProductCategoryTagId(int productCategoryTagId) {
        this.productCategoryTagId = productCategoryTagId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
