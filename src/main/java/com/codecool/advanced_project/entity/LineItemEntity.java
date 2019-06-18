package com.codecool.advanced_project.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LineItemEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String quantity;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private Boolean isArchived;

    @EqualsAndHashCode.Exclude
    @OneToOne
    private ProductEntity product;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private ShoppingListEntity shoppingList;

}
