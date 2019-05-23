package com.codecool.advanced_project.service;

import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.service.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public ProductService (ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product findById(int id) {
        return productDao.find(id);
    }

    public void add(Product newProduct) {
        productDao.add(newProduct);
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }
}
