package com.codecool.advanced_project.service;

import com.codecool.advanced_project.dao.ShoppingListDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CreatorConfig {

    @Bean
    public ShoppingListDao createShoppingListDao(DataSource dataSource) {
        return new ShoppingListDao(dataSource);
    }
}
