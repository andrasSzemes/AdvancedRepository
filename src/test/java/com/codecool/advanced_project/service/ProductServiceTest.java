package com.codecool.advanced_project.service;

import com.codecool.advanced_project.dao.ProductDao;
import com.codecool.advanced_project.dao.implementation.ProductDaoImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    ProductDao productDao = ProductDaoImpl.getInstance();

    @Test
    void findById() {
        ProductService productService = new ProductService(productDao);
        productService.findById(1);
        Mockito.verify(productDao).find(1);
    }
}