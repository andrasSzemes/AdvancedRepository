package com.codecool.advanced_project.config;

import com.codecool.advanced_project.service.dao.implementation.ShoppingListDaoDb;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean(name = "ShoppingListDb")
    public ShoppingListDaoDb shoppingListDaoDb() {
        return new ShoppingListDaoDb();
    }

}
