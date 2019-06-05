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
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private ProductCategoryEntity category;

    @EqualsAndHashCode.Exclude
    private String pictureUrl;

    @ManyToMany //todo check mapping
    @EqualsAndHashCode.Exclude
    private List<CustomTagEntity> tags;

    @OneToOne(mappedBy = "product")
    private LineItemEntity lineItemEntity;
}


