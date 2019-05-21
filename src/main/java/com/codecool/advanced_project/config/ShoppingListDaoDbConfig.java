package com.codecool.advanced_project.config;

import com.codecool.advanced_project.model.LineItem;
import com.codecool.advanced_project.model.ShoppingList;
import com.codecool.advanced_project.service.dao.LineItemDao;
import com.codecool.advanced_project.service.dao.MemberGroupsDao;
import com.codecool.advanced_project.service.dao.ShoppingListDao;
import com.codecool.advanced_project.service.dao.implementation.ShoppingListDaoDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class ShoppingListDaoDbConfig {
    @Autowired
    private LineItemDao LineItemDaoInUse;
    @Autowired
    private MemberGroupsDao MemberGroupsDaoInUse;

    @Bean(name = "getAllLineItem")
    public Function<Integer, List<LineItem>> provideGetAllLineItem() {
        return LineItemDaoInUse::getAll;
    }

    @Bean(name = "getGroupIds")
    public Function<Integer, List<Integer>> provideGetGroupIds() {
        return MemberGroupsDaoInUse::getGroupIds;
    }
}
