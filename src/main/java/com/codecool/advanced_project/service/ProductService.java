package com.codecool.advanced_project.service;

import com.codecool.advanced_project.dao.ProductDao;
import com.codecool.advanced_project.model.Product;

public class ProductService {

    private ProductDao productDao;

    public ProductService (ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product findById(int id) {
        return productDao.find(id);
    }

}
