package com.codecool.advanced_project.repository;

import com.codecool.advanced_project.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE ShopEntity shop set shop.name = :#{#entity.name}, shop.address = :#{#entity.address} where shop.id = :id")
    int updateShop(@Param("entity") ShopEntity shopEntity, @Param("id") Long id);
}
