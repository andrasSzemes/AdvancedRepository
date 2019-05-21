package com.codecool.advanced_project.model;

public class LineItem {
    private int id;
    private String quantity;
    private boolean isArchived;
    private int productId;

    public LineItem(int id, String quantity, boolean isArchived, int productId) {
        this.id = id;
        this.quantity = quantity;
        this.isArchived = isArchived;
        this.productId = productId;
    }

    public LineItem() {

    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", quantity='" + quantity + '\'' +
                ", isArchived=" + isArchived +
                ", productId=" + productId +
                '}';
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
