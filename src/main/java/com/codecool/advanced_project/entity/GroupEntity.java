package com.codecool.advanced_project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column(unique = true)
    private String keyForConnection;

    @ManyToMany
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private List<AppUser> members = new ArrayList<>();

    @Override
    public String toString() {
        return "GroupEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idOfOwner=" + idOfOwner +
                ", keyForConnection='" + keyForConnection + '\'' +
                '}';
    }
}
