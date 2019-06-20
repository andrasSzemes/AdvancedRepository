package com.codecool.advanced_project.entity;

import com.codecool.advanced_project.entity.helper.GroupEntityJSON;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue
    Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    @ManyToMany(mappedBy = "members")
    @Builder.Default
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private List<GroupEntity> groups = new ArrayList<>();

    // roles of the user (ADMIN, USER,..)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private List<String> roles = new ArrayList<>();

    public List<GroupEntityJSON> getGroupJSONs() {
        List<GroupEntityJSON> list = new ArrayList();
        for (GroupEntity group : groups) {
            list.add(new GroupEntityJSON(group.getId(), group.getName()));
        }
        return list;
    }
}
