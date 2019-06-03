package com.codecool.advanced_project;

import com.codecool.advanced_project.service.ProductServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdvancedProjectApplication {

    @Autowired
    private ProductServiceJPA productServiceJPA;

    public static void main(String[] args) {
        SpringApplication.run(AdvancedProjectApplication.class, args);
    }

}
