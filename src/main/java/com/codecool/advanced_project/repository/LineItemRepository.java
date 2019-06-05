package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.LineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItemEntity, Long> {
}
