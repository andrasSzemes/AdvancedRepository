package com.codecool.advanced_project.dao.implementation;

import com.codecool.advanced_project.dao.ProductCategoryDao;
import com.codecool.advanced_project.dao.ProductDao;
import com.codecool.advanced_project.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private List<Product> data = new ArrayList<>();
    private static ProductDaoImpl instance = null;
    private ProductCategoryDao productCategoryDao = ProductCategoryImpl.getInstance();

    private ProductDaoImpl() {
    }

    public static ProductDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        try (
                Connection conn = getConnection();

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO product (name, category_tag_id) values (?, ?)")
        ) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory().getName());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
    }

    @Override
    public Product find(int id) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select product.name, product.category_tag_id as categoryid" +
                                " from product join category_tag on product.category_tag_id = category_tag.id " +
                                " where product.id = (?)")
        ) {
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return getProduct(resultSet);
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
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM product WHERE id = (?)")
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
    }

    @Override
    public void removeAll() {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM product")
        ) {
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> resultList = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product")
        ) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                resultList.add(product);
            }
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
        return resultList;
    }

    @Override
    public List<Product> getBy(String category) {
        return null;
    }

    @Override
    public void addPicture(int id, String url) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO product (picture_url) values (?)")
        ) {
            stmt.setString(1, url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getString("name"), productCategoryDao.find(resultSet.getInt("categoryid")));
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/local_test",
                "csepelyd",
                "csepelyd");
    }
}
