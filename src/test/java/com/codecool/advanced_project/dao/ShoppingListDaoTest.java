package com.codecool.advanced_project.dao;

import com.codecool.advanced_project.model.ShoppingList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListDaoTest {

    private SpringJdbcConfig springJdbcConfig = new SpringJdbcConfig();
    private DataSource dataSource = springJdbcConfig.postgresqlDataSource();
    private ShoppingListDao shoppingListDao = new ShoppingListDao(dataSource);
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    private String shopListTable = "shopping_list";

    @BeforeEach
    void clear() {
        truncateShoppingListTable();
    }

    @Test
    void testSaveNewShoppingListAddRecordToTable() {
        ShoppingList shoppingList = new ShoppingList(1234);

        for (int i=1; i<7; i++) {
            shoppingListDao.saveNewShoppingList(shoppingList);
            assertEquals(i, getNumberOfRowsInTable(shopListTable));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1,33,456,912,Integer.MAX_VALUE})
    void testSaveNewShoppingListWritesCorrectGroupId(int groupId) {
        ShoppingList shoppingList = new ShoppingList(groupId);

        shoppingListDao.saveNewShoppingList(shoppingList);
        assertEquals(groupId, getLastInsertedListsGroupId());
    }

    @AfterAll
    static void clearAfterTests() {
        new ShoppingListDaoTest().truncateShoppingListTable();
    }

    private int getLastInsertedListsGroupId() {
        String sql = "SELECT group_id FROM %s " +
                     "ORDER BY id DESC " +
                     "LIMIT 1";
        String formSql = String.format(sql, shopListTable);

        return jdbcTemplate.queryForObject(formSql, Integer.class);
    }

    private int getNumberOfRowsInTable(String tableName) {
        String sql = "SELECT COUNT(*) FROM %s";
        String formSql = String.format(sql, shopListTable);

        return jdbcTemplate.queryForObject(formSql, Integer.class);
    }

    private void truncateShoppingListTable() {
        String sql = "TRUNCATE TABLE %s; ALTER SEQUENCE %s_id_seq RESTART WITH 1;";
        String formSql = String.format(sql, shopListTable, shopListTable);

        jdbcTemplate.execute(formSql);
    }


}