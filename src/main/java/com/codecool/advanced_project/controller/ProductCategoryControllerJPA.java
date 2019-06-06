package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.entity.ProductCategoryEntity;
import com.codecool.advanced_project.service.ProductCategoryServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product-categorys")
public class ProductCategoryControllerJPA {

    @Autowired
    private ProductCategoryServiceJPA productCategoryServiceJPA;

    @GetMapping("/{id}")
    public ProductCategoryEntity findProductCategory(@PathVariable("id") Long id) {
        return this.productCategoryServiceJPA.findById(id);
    }

    @PostMapping("")
    public ProductCategoryEntity addProductCategory(@RequestBody @Valid ProductCategoryEntity newProductCategory) {
        productCategoryServiceJPA.add(newProductCategory);
        return newProductCategory;
    }

    @GetMapping("")
    public List<ProductCategoryEntity> listProductCategories() {
        return productCategoryServiceJPA.getAll();
    }
}
