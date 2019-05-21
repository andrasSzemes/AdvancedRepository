package com.codecool.advanced_project.dao.implementation;

import com.codecool.advanced_project.dao.ProductCategoryDao;
import com.codecool.advanced_project.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryImpl implements ProductCategoryDao {

    private static ProductCategoryImpl instance;

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
            System.out.println("sqlerror" + e);
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
            System.out.println("sqlerror" + e);
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
            System.out.println("sqlerror" + e);
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
            System.out.println("sqlerror" + e);
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
            System.out.println("sqlerror" + e);
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
            System.out.println("sqlerror" + e);
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
