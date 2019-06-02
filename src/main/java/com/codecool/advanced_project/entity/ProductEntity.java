package com.codecool.advanced_project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private ProductCategoryEntity category;

    @EqualsAndHashCode.Exclude
    private String pictureUrl;

    @ManyToMany
    private List<CustomTagEntity> tags;
}


