package com.codecool.advanced_project.config;

import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.service.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class LineItemDaoConfig {
    @Autowired
    private ProductDao productDaoInUse;

    @Bean(name = "getProduct")
    public Function<Integer, Product> provideGetProduct() {
        return productDaoInUse::find;
    }
}
