package com.codecool.advanced_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdvancedProjectApplication {

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(AdvancedProjectApplication.class, args);
    }

}
