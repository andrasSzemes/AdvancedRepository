package com.codecool.advanced_project.model;

import java.util.List;

public class ShoppingList {
    private int id;
    private int associatedShopId;
    private boolean isArchived;
    private int memberId;
    private int groupId;
    private List<LineItem> lineItems;

    public ShoppingList(int id, int associatedShopId, boolean isArchived, int memberId, int groupId, List<LineItem> lineItems) {
        this.id = id;
        this.associatedShopId = associatedShopId;
        this.isArchived = isArchived;
        this.memberId = memberId;
        this.groupId = groupId;
        this.lineItems = lineItems;
    }

    public ShoppingList() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssociatedShopId() {
        return associatedShopId;
    }

    public void setAssociatedShopId(int associatedShopId) {
        this.associatedShopId = associatedShopId;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", associatedShopId=" + associatedShopId +
                ", isArchived=" + isArchived +
                ", memberId=" + memberId +
                ", groupId=" + groupId +
                ", lineItems=" + lineItems +
                '}';
    }
}
