package com.codecool.advanced_project.dao.implementation;

import com.codecool.advanced_project.dao.ProductCategoryDao;
import com.codecool.advanced_project.dao.ProductDao;
import com.codecool.advanced_project.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {
    private List<Product> data = new ArrayList<>();
    private static ProductDaoImpl instance = null;
    private ProductCategoryDao productCategoryDao = ProductCategoryImpl.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryImpl.class);

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
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO product (name) values (?)")

        ) {
            stmt.setString(1, product.getName());
            stmt.executeQuery();

        } catch (Exception e) {
            logger.error("ProductDao/add: " + e.toString());
            e.printStackTrace();
        }
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT id FROM product WHERE name=(?)")
        ) {
            stmt.setString(1, product.getName());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            logger.error("ProductDao/add(getID): " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) throws NullPointerException {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT product.name, product.category_tag_id as categoryid" +
                                " FROM product JOIN category_tag ON product.category_tag_id = category_tag.id " +
                                " WHERE product.id = (?)")
        ) {
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return getProduct(resultSet);
            }

        } catch (Exception e) {
            logger.error("ProductDao/find: " + e.getMessage());
            e.printStackTrace();

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
            logger.error("ProductDao/remove: " + e.getMessage());
             e.printStackTrace();
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
            logger.error("ProductDao/removeAll: " + e.getMessage());
             e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> resultList = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT product.name, product.category_tag_id as categoryid FROM product")
        ) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                resultList.add(product);
            }
        } catch (Exception e) {
            logger.error("ProductDao/getAll: " + e.getMessage());
             e.printStackTrace();
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
            logger.error("ProductDao/addPicture: " + e.getMessage());
             e.printStackTrace();
        }
    }

    @Override
    public void rename(int id, String newName) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE  product SET name = (?) WHERE id = (?)")
        ) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("ProductDao/rename: " + e.getMessage());
             e.printStackTrace();
        }
    }

    @Override
    public void updateTags(int id, List<String> tags) {

    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getString("name"), productCategoryDao.findById(resultSet.getInt("categoryid")));
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/local_test",
                "csepelyd",
                "csepelyd");
    }
}
