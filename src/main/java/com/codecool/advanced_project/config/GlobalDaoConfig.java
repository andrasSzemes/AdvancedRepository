package com.codecool.advanced_project.config;

import com.codecool.advanced_project.service.ShoppingListDao;
import com.codecool.advanced_project.service.dao.implementation.ShoppingListDaoDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class GlobalDaoConfig {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public ShoppingListDao getShoppingListDaoImplementation() {
        return new ShoppingListDaoDb(jdbcTemplate);
    }
}
