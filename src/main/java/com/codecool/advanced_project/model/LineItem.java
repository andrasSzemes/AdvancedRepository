package com.codecool.advanced_project.model;

import java.util.Objects;

public class LineItem {
    private int id;
    private String quantity;
    private boolean isArchived;
    private Product product;

    public LineItem(int id, String quantity, boolean isArchived, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.isArchived = isArchived;
        this.product = product;
    }

    public LineItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return id == lineItem.id &&
                isArchived == lineItem.isArchived &&
                Objects.equals(quantity, lineItem.quantity) &&
                Objects.equals(product, lineItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, isArchived, product);
    }
}
