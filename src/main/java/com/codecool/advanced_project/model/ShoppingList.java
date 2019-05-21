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

}
