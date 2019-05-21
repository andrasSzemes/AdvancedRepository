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
}
