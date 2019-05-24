package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.model.ProductCategory;
import com.codecool.advanced_project.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product-category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/find/{id}")
    public ProductCategory findProductCategory(@PathVariable("id") int id) {
        return this.productCategoryService.findById(id);
    }

    @PostMapping("/add")
    public ProductCategory addProductCategory(@RequestBody @Valid ProductCategory newProductCategory) {
        this.productCategoryService.add(newProductCategory);
        return newProductCategory;
    }

    @GetMapping("/list/all")
    public List<ProductCategory> listProductCategories() {
        return this.productCategoryService.getAll();
    }
}
