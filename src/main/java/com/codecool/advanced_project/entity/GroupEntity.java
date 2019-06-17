package com.codecool.advanced_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupEntity {
    @Id
    @GeneratedValue
    Long id;

    private String name;

    private Long idOfOwner;

    private String keyForConnection;

    @ManyToMany
    private List<AppUser> members;
}
