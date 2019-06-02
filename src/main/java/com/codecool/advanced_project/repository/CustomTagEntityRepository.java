package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.CustomTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomTagEntityRepository extends JpaRepository<CustomTagEntity, Integer> {

    CustomTagEntity findByIdAnd(Integer id);

    CustomTagEntity findByName(String name);
}
