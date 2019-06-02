package com.codecool.advanced_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShoppingListEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private int associatedShopId = -1;
    private boolean isArchived = false;
    private int memberId = -1;
    private int groupId;
    private List<LineItemEntity> lineItems;

    /**
     * This constructor should be used, if a new list is created
     *
     * @param groupId shared account's identifier
     */
    public ShoppingListEntity(int groupId) {
        this.groupId = groupId;
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

    public List<LineItemEntity> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemEntity> lineItems) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListEntity that = (ShoppingListEntity) o;
        return id == that.id &&
                associatedShopId == that.associatedShopId &&
                isArchived == that.isArchived &&
                memberId == that.memberId &&
                groupId == that.groupId &&
                Objects.equals(lineItems, that.lineItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, associatedShopId, isArchived, memberId, groupId, lineItems);
    }
}
