package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.model.ProductCategory;
import com.codecool.advanced_project.service.dao.MemberGroupsDao;
import com.codecool.advanced_project.service.dao.ProductDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductDaoDbTest {
    private static ProductDao productDao;
    private static JdbcTemplate jdbcTemplateMock;

    @BeforeAll
    static void setUp() {
        jdbcTemplateMock = mock(JdbcTemplate.class);
        productDao = new ProductDaoDb(jdbcTemplateMock);
    }

    @Test
    void getProductWithInvalidId() {
        when(jdbcTemplateMock.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenThrow(EmptyResultDataAccessException.class);

        assertNull(productDao.find(1));
    }

    @Test
    void getProductWithValidId() {
        ProductCategory category = new ProductCategory("category");
        Product product = new Product(1, "product", category, "picture");
        when(jdbcTemplateMock.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(product);

        assertEquals(product, productDao.find(1));
    }
}