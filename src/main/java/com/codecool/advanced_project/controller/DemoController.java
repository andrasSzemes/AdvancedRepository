package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.DemoProduct;
import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.model.ShoppingList;
import com.codecool.advanced_project.model.mapper.DemoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/products")
    public List<DemoProduct> getUsersLatest() {
        List<DemoProduct> list = new ArrayList<>();
        List<Map<String, Object>> query = jdbcTemplate.queryForList("SELECT * FROM demo");
        for (Map<String, Object> stringObjectMap : query) {
            DemoProduct demoProduct = new DemoProduct();
            demoProduct.setId((Integer) stringObjectMap.get("id"));
            demoProduct.setTitle((String) stringObjectMap.get("title"));
            demoProduct.setCompleted((Boolean) stringObjectMap.get("completed"));
            list.add(demoProduct);
        }
        return list;
    }
}
