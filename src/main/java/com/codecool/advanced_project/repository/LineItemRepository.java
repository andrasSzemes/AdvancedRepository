package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.LineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.beans.Transient;

public interface LineItemRepository extends JpaRepository<LineItemEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE LineItemEntity l SET l.isArchived = :bool where l.id = :id")
    int updateArchivedState(@Param("id") Long id, @Param("bool") boolean bool);

}
