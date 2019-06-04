package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
}
