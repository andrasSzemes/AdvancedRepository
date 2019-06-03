package com.codecool.advanced_project.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
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
    private Long id;

    private Long associatedShopId = -1L;

    private Boolean isArchived = false;

    private Long memberId = -1L;

    @NotNull
    private Long groupId;

    @OneToMany(mappedBy = "shoppingList")
    @EqualsAndHashCode.Exclude
    private List<LineItemEntity> lineItems;

}
