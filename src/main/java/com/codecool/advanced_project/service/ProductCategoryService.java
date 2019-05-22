package com.codecool.advanced_project.service;

import com.codecool.advanced_project.dao.ProductCategoryDao;
import com.codecool.advanced_project.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    public ProductCategoryService(ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    public ProductCategory findById(int id) {
        return productCategoryDao.findById(id);
    }

    public ProductCategory findByName(String name) {
        return productCategoryDao.findByName(name);
    }

    public void add(ProductCategory newProductCategory) {
        productCategoryDao.add(newProductCategory);
    }

    public List<ProductCategory> getAll() {
        return productCategoryDao.getAll();
    }

}
