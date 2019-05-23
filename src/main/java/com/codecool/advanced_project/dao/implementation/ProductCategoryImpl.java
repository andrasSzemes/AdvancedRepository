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

    private static ProductCategoryImpl instance = null;
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryImpl.class);

    private ProductCategoryImpl() {
    }

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

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO category_tag (name) values (?) RETURNING id")
        ) {
            stmt.setString(1, category.getName());

            ResultSet resultSet = stmt.executeQuery();

            category.setId(resultSet.getInt("id"));

        } catch (Exception e) {
            logger.error("ProductCategoryDao/add: " + e.getMessage());
            e.printStackTrace();
        }
        try (
                Connection conn = getConnection();

                PreparedStatement stmt = conn.prepareStatement("SELECT id FROM category_tag WHERE name = (?)")
        ) {
            stmt.setString(1, category.getName());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                category.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            logger.error("ProductCategoryDao/add(getID): " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory findById(int id) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category_tag WHERE id = (?)")
        ) {
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            return new ProductCategory(resultSet.getString("name"));

        } catch (Exception e) {
            logger.error("ProductCategoryDao/find: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductCategory findByName(String name) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM  category_tag WHERE name = (?)")
        ) {
            stmt.setString(1, name);

            ResultSet resultSet = stmt.executeQuery();
            return new ProductCategory(resultSet.getString("id"));
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
