package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.CustomTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomTagEntityRepository extends JpaRepository<CustomTagEntity, Long> {

    Optional<CustomTagEntity> findById(Long id);
}
