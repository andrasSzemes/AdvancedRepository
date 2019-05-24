package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.model.ProductCategory;
import com.codecool.advanced_project.model.mapper.ProductRowMapper;
import com.codecool.advanced_project.service.dao.ProductCategoryDao;
import com.codecool.advanced_project.service.dao.ProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDaoDb implements ProductDao {

    private JdbcTemplate jdbcTemplate;
    private ProductCategoryDao productCategoryDao;
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryDb.class);

    @Autowired
    public ProductDaoDb(ProductCategoryDao productCategoryDao, JdbcTemplate jdbcTemplate) {
        this.productCategoryDao = productCategoryDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product find(int id) throws NullPointerException {
        String sql = "SELECT * FROM product WHERE id = (?)";

        Product product = null;
        try {
            product = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper(productCategoryDao));
        }
        catch (EmptyResultDataAccessException e) {
            logger.error("ProductDao/find: " + e.getMessage());
        }

        return product;
    }

    @Override
    public void add(Product product) {
        String sql = "INSERT INTO product (name) values (?) RETURNING id";
        Map<String, Object> queryRows = jdbcTemplate.queryForMap(sql, product.getName());

        product.setId((int) queryRows.get("id"));
//        logger.error("ProductDao/add(getID): " + e.toString());
    }

    @Override
    public void remove(int id) {
        String sql = "DELETE FROM product WHERE id = (?)";
        jdbcTemplate.update(sql, new int[]{id});
//        logger.error("ProductDao/remove: " + e.getMessage());
    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM product";
        jdbcTemplate.execute(sql);
//        logger.error("ProductDao/removeAll: " + e.getMessage());
    }

    @Override
    public List<Product> getAll() {
        List<Product> resultList = new ArrayList<>();

        String sql = "SELECT * FROM product";
        List<Map<String, Object>> queryRows = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> queryRow : queryRows) {
            Product product = createProduct(queryRow);
            resultList.add(product);
        }
//        logger.error("ProductDao/getAll: " + e.getMessage());
        return resultList;
    }

    private Product createProduct(Map<String, Object> queryRow) {
        Product product = new Product();

        product.setId((int) queryRow.get("id"));
        product.setName((String) queryRow.get("name"));
        if (queryRow.get("category_tag_id") != null) {
            ProductCategory category = productCategoryDao.findById((int) queryRow.get("category_tag_id"));
            product.setCategory(category);
        }
        product.setPicture((String) queryRow.get("picture_url"));
        return product;
    }

    @Override
    public List<Product> getBy(String category) {
        return null;
    }

    @Override
    public void addPicture(int id, String url) {
        String sql = "INSERT INTO product (picture_url) values (?)";
        jdbcTemplate.update(sql, url);
//        logger.error("ProductDao/addPicture: " + e.getMessage());
    }

    @Override
    public void rename(int id, String newName) {
        String sql = "UPDATE  product SET name = (?) WHERE id = (?)";
        jdbcTemplate.update(sql, new Object[]{newName, id}, new int[]{Types.VARCHAR, Types.BIGINT});
//        logger.error("ProductDao/rename: " + e.getMessage());
    }

    @Override
    public void updateTags(int id, List<String> tags) {

    }
}
