package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.CustomTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomTagEntityRepository extends JpaRepository<CustomTagEntity, Long> {

    CustomTagEntity find(Long id);

    CustomTagEntity findByName(String name);
}
