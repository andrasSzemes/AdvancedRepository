package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.model.ShoppingList;
import com.codecool.advanced_project.service.ShoppingListDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

class ShoppingListDaoDbTest {
    private JdbcTemplate jdbcTemplateMock = Mockito.mock(JdbcTemplate.class);
    private ShoppingListDao shoppingListDaoMock = new ShoppingListDaoDb(jdbcTemplateMock);


    @Test
    void testSaveNewShoppingListAddRecordToTable() {
        String query = "INSERT INTO shopping_list(associated_shop_id, archived, member_id, group_id)VALUES (-1,FALSE,-1,?)";
        int groupId = 1234;
        ShoppingList shoppingList = new ShoppingList(groupId);

        when(jdbcTemplateMock.update(query, groupId)).thenReturn(1);
        shoppingListDaoMock.saveNew(shoppingList);

        verify(jdbcTemplateMock).update(query, groupId);
    }

}