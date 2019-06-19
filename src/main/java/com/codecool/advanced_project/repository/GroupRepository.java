package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByKeyForConnection(String groupKey);

//    @Query("UPDATE GroupEntity g SET g.members = :#{#group.members} WHERE g.id = :#{#group.id}")
//    int updateMembers(@Param("group") GroupEntity groupEntity);

}
