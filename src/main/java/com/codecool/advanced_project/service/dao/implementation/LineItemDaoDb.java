package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.model.LineItem;
import com.codecool.advanced_project.model.Product;
import com.codecool.advanced_project.service.dao.LineItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class LineItemDaoDb implements LineItemDao {
    private JdbcTemplate jdbcTemplate;
    private Function<Integer, Product> getProduct;

    @Autowired
    public LineItemDaoDb(JdbcTemplate jdbcTemplate, Function<Integer, Product> getProduct) {
        this.jdbcTemplate = jdbcTemplate;
        this.getProduct = getProduct;
    }

    @Override
    public List<LineItem> getAll(int shoppingListId) {
        List<LineItem> lineItems = new ArrayList<>();

        String query = "SELECT * FROM line_item " +
                "LEFT JOIN list_line_items ON(line_item.id = list_line_items.line_item_id) " +
                "WHERE list_line_items.shopping_list_id=" +
                shoppingListId;

        List<Map<String, Object>> queryRows = jdbcTemplate.queryForList(query);

        for (Map<String, Object> queryRow : queryRows) {
            LineItem lineItem = new LineItem();
            lineItem.setId((Integer) queryRow.get("id"));
            lineItem.setQuantity((String) queryRow.get("quantity"));
            lineItem.setArchived((Boolean) queryRow.get("archived"));
            int productId = (Integer) queryRow.get("product_id");
            lineItem.setProduct(getProduct.apply(productId));
            lineItems.add(lineItem);
        }
        return lineItems;
    }
}
