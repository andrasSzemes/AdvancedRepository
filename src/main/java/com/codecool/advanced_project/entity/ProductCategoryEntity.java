package com.codecool.advanced_project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductCategoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToMany
    private List<ProductEntity> products;
}
