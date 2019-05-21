package com.codecool.advanced_project.config;

import com.codecool.advanced_project.service.dao.LineItemDao;
import com.codecool.advanced_project.service.dao.MemberGroupsDao;
import com.codecool.advanced_project.service.dao.ShoppingListDao;
import com.codecool.advanced_project.service.dao.implementation.LineItemDaoDb;
import com.codecool.advanced_project.service.dao.implementation.MemberGroupsDaoDb;
import com.codecool.advanced_project.service.dao.implementation.ShoppingListDaoDb;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalDaoConfig {

    @Bean(name = "ShoppingListDaoInUse")
    public ShoppingListDao getShoppingListDaoImplementation() {
        return new ShoppingListDaoDb();
    }

    @Bean(name = "LineItemDaoInUse")
    public LineItemDao getLineItemDaoImplementation() {
        return new LineItemDaoDb();
    }

    @Bean(name = "MemberGroupsDaoInUse")
    public MemberGroupsDao getMemberGroupsDaoImplementation() {
        return new MemberGroupsDaoDb();
    }
}
