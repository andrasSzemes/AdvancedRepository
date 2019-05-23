package com.codecool.advanced_project.config;

import com.codecool.advanced_project.model.LineItem;
import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.service.dao.LineItemDao;
import com.codecool.advanced_project.service.dao.MemberGroupsDao;
import com.codecool.advanced_project.service.dao.ProductDao;
import com.codecool.advanced_project.service.dao.ShoppingListDao;
import com.codecool.advanced_project.service.dao.implementation.LineItemDaoDb;
import com.codecool.advanced_project.service.dao.implementation.MemberGroupsDaoDb;
import com.codecool.advanced_project.service.dao.implementation.ProductDaoDb;
import com.codecool.advanced_project.service.dao.implementation.ShoppingListDaoDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.function.Function;

@Configuration
public class GlobalDaoConfig {
    private JdbcTemplate jdbcTemplate;
    private Function<Integer, List<LineItem>> getAllLineItem;
    private Function<Integer, List<Integer>> getGroupIds;
    private Function<Integer, Product> findProduct;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setGetAllLineItem(Function<Integer, List<LineItem>> getAllLineItem) {
        this.getAllLineItem = getAllLineItem;
    }

    @Autowired
    public void setGetGroupIds(Function<Integer, List<Integer>> getGroupIds) {
        this.getGroupIds = getGroupIds;
    }

    @Autowired
    public void setFindProduct(Function<Integer, Product> findProduct) {
        this.findProduct = findProduct;
    }

    @Bean(name = "ShoppingListDaoInUse")
    public ShoppingListDao getShoppingListDaoImplementation() {
        return new ShoppingListDaoDb(jdbcTemplate, getAllLineItem, getGroupIds);
    }

    @Bean(name = "LineItemDaoInUse")
    public LineItemDao getLineItemDaoImplementation() {
        return new LineItemDaoDb(jdbcTemplate, findProduct);
    }

    @Bean(name = "MemberGroupsDaoInUse")
    public MemberGroupsDao getMemberGroupsDaoImplementation() {
        return new MemberGroupsDaoDb(jdbcTemplate);
    }

    @Bean(name = "ProductDaoInUse")
    public ProductDao getProductDaoImplementation() {
        return new ProductDaoDb(jdbcTemplate);
    }
}
