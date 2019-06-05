package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
}
