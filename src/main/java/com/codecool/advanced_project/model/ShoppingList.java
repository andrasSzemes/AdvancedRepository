package com.codecool.advanced_project.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private int id;
    private int memberId = -1;
    private int groupId;
    private int associatedShop = -1;
    private boolean archived = false;

    /**
     * This constructor should be used, if a new list is created
     *
     * @param groupId shared account's identifier
     */
    public ShoppingList(int groupId) {
        this.groupId = groupId;
    }

    public ShoppingList() {
        //Needed for JSON sending
    }

    public int getGroupId() {
        return groupId;
    }

}
