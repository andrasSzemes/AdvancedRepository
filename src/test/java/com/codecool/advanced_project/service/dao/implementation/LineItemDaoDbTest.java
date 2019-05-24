package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.model.LineItem;
import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.model.ProductCategory;
import com.codecool.advanced_project.service.dao.LineItemDao;
import com.codecool.advanced_project.service.dao.ProductDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LineItemDaoDbTest {
    private static LineItemDao lineItemDao;
    private static JdbcTemplate jdbcTemplateMock;
    private static ProductDao productDaoMock;
    private List<LineItem> lineItemList = new ArrayList<>();
    private List<Map<String, Object>> lineItemMapList = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        jdbcTemplateMock = mock(JdbcTemplate.class);
        productDaoMock = mock(ProductDaoDb.class);
        lineItemDao = new LineItemDaoDb(jdbcTemplateMock, productDaoMock::find);
    }

    @BeforeEach
    void setLineItemMapList() {
        Map<String, Object> lineItemMap = new HashMap<>();
        lineItemMap.put("id", 1);
        lineItemMap.put("quantity", "1 valami");
        lineItemMap.put("archived", false);
        lineItemMap.put("product_id", 0);
        lineItemMapList.add(lineItemMap);
    }

    @Test
    void getShoppingListWithNoConnectedProduct() {
        LineItem lineItem = new LineItem(1, "1 valami", false, null);
        lineItemList.add(lineItem);
        when(jdbcTemplateMock.queryForList(anyString())).thenReturn(lineItemMapList);
        when(productDaoMock.find(anyInt())).thenReturn(null);

        assertEquals(lineItemList, lineItemDao.getAll(1));
    }

    @Test
    void getShoppingListWithConnectedProduct() {
        ProductCategory category = new ProductCategory("category");
        Product product = new Product(1, "product", category, "picture");
        LineItem lineItem = new LineItem(1, "1 valami", false, product);
        lineItemList.add(lineItem);
        when(jdbcTemplateMock.queryForList(anyString())).thenReturn(lineItemMapList);
        when(productDaoMock.find(anyInt())).thenReturn(product);

        assertEquals(lineItemList, lineItemDao.getAll(1));
    }

    @Test
    void getShoppingListForInvalidId() {
        lineItemMapList.clear();
        when(jdbcTemplateMock.queryForList(anyString())).thenReturn(lineItemMapList);

        assertEquals(new ArrayList<>(), lineItemDao.getAll(1));
    }
}