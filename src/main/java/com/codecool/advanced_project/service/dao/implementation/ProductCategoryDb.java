package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.service.dao.ProductCategoryDao;
import com.codecool.advanced_project.model.ProductCategory;
import com.codecool.advanced_project.model.mapper.ProductCategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductCategoryDb implements ProductCategoryDao {

    //TODO: jdbcTemplate do not throw exception, but log should be added..
    //private static final Logger logger = LoggerFactory.getLogger(ProductCategoryImpl.class);
    private JdbcTemplate jdbcTemplate;

    public ProductCategoryDb() {
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(ProductCategory category) {
        String sql = "INSERT INTO category_tag (name) values (?) RETURNING id";
        Map<String, Object> queryRows = jdbcTemplate.queryForMap(sql, category.getName());

        //Product adding might need the id for saving, if it is added with not existing category
        category.setId((int) queryRows.get("id"));
    }

    @Override
    public ProductCategory findById(int id) {
        String sql = "SELECT * FROM category_tag WHERE id = (?)";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductCategoryRowMapper());
    }

    @Override
    public ProductCategory findByName(String name) {
        String sql = "SELECT * FROM  category_tag WHERE name = (?)";

        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new ProductCategoryRowMapper());
    }


    @Override
    public void remove(int id) {
        String sql = "DELETE FROM category_tag WHERE id = (?)";
        jdbcTemplate.update(sql, new int[]{id});

        //logger.error("ProductCategoryDao/remove: " + e.getMessage());
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> resultList = new ArrayList<>();

        String sql = "SELECT * FROM category_tag";
        List<Map<String, Object>> queryRows = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> queryRow : queryRows) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId((Integer) queryRow.get("id"));
            productCategory.setName((String) queryRow.get("name"));
            resultList.add(productCategory);
        }

        //logger.error("ProductCategoryDao/getAll: " + e.getMessage());
        return resultList;
    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM category_tag";
        jdbcTemplate.execute(sql);

        //logger.error("ProductCategoryDao/removeAll: " + e.getMessage());
    }

    @Override
    public Integer getId(String name) {
        String sql = "SELECT id FROM category_tag WHERE name = (?)";
        Map<String, Object> queryRows = jdbcTemplate.queryForMap(sql, name);

        return (Integer) queryRows.get("id");
        //logger.error("ProductCategoryDao/getId: " + e.getMessage());
    }

}
