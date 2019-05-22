package com.codecool.advanced_project.dao.implementation;

import com.codecool.advanced_project.dao.ProductCategoryDao;
import com.codecool.advanced_project.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryImpl implements ProductCategoryDao {

    private static ProductCategoryImpl instance;
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryImpl.class);

    public static ProductCategoryImpl getInstance() {
        if (instance == null) {
            instance = new ProductCategoryImpl();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        try (
                Connection conn = getConnection();

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO category_tag (name) values (?)")
        ) {
            stmt.setString(1, category.getName());

            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error("ProductCategoryDao/add: " + e.getMessage());
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category_tag WHERE id = (?)")
        ) {
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new ProductCategory(resultSet.getString("name"));
            }

        } catch (Exception e) {
            logger.error("ProductCategoryDao/find: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM category_tag WHERE id = (?)")
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error("ProductCategoryDao/remove: " + e.getMessage());
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> resultList = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category_tag")
        ) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                ProductCategory category = getProductCategory(resultSet);
                resultList.add(category);
            }
        } catch (Exception e) {
            logger.error("ProductCategoryDao/getAll: " + e.getMessage());
        }
        return resultList;
    }

    @Override
    public void removeAll() {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM category_tag")
        ) {
            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error("ProductCategoryDao/removeAll: " + e.getMessage());
        }
    }

    @Override
    public Integer getId(String name) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT id FROM category_tag WHERE name = (?)")
        ) {
            stmt.setString(1, name);

            ResultSet resultSet = stmt.executeQuery();
            return resultSet.getInt("id");


        } catch (Exception e) {
            logger.error("ProductCategoryDao/getId: " + e.getMessage());
        }
        return null;
    }

    private ProductCategory getProductCategory(ResultSet resultSet) throws SQLException {
        return new ProductCategory(resultSet.getString("name"));
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/local_test",
                "csepelyd",
                "csepelyd");
    }
}
