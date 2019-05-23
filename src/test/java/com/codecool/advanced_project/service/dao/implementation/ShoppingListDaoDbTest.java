package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.model.ShoppingList;
import com.codecool.advanced_project.service.dao.LineItemDao;
import com.codecool.advanced_project.service.dao.MemberGroupsDao;
import com.codecool.advanced_project.service.dao.ShoppingListDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ShoppingListDaoDbTest {
    private static ShoppingListDao shoppingListDao;
    private static JdbcTemplate jdbcTemplateMock;
    private static LineItemDao lineItemDaoMock;
    private static MemberGroupsDao memberGroupsDaoMock;
    private ShoppingList shoppingList = new ShoppingList();
    private ShoppingList expectedShoppingList = new ShoppingList();

    @BeforeAll
    static void setUp() {
        jdbcTemplateMock = mock(JdbcTemplate.class);
        lineItemDaoMock = mock(LineItemDaoDb.class);
        memberGroupsDaoMock = mock(MemberGroupsDaoDb.class);
        shoppingListDao = new ShoppingListDaoDb(jdbcTemplateMock, lineItemDaoMock::getAll, memberGroupsDaoMock::getGroupIds);
    }

    @BeforeEach
    void setShoppingList() {
        shoppingList.setId(1);
        shoppingList.setAssociatedShopId(1);
        shoppingList.setGroupId(1);
        shoppingList.setMemberId(1);
        shoppingList.setArchived(false);
    }

    @BeforeEach
    void setExpectedShoppingList() {
        expectedShoppingList.setId(1);
        expectedShoppingList.setAssociatedShopId(1);
        expectedShoppingList.setGroupId(1);
        expectedShoppingList.setMemberId(1);
        expectedShoppingList.setArchived(false);
    }

    @Test
    void getShoppingListForInvalidUserId() {
        when(memberGroupsDaoMock.getGroupIds(anyInt())).thenReturn(new ArrayList<>());
        when(jdbcTemplateMock.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenThrow(EmptyResultDataAccessException.class);

        assertNull(shoppingListDao.getLatest(1));
    }

    @Test
    void getShoppingListForValidUserWithoutGroup() {
        when(memberGroupsDaoMock.getGroupIds(anyInt())).thenReturn(new ArrayList<>());
        when(lineItemDaoMock.getAll(anyInt())).thenReturn(new ArrayList<>());
        when(jdbcTemplateMock.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(shoppingList);
        expectedShoppingList.setLineItems(new ArrayList<>());

        assertEquals(expectedShoppingList, shoppingListDao.getLatest(1));
    }

    @Test
    void getShoppingListForValidUserWithGroups() {
        List<Integer> groupIds = new ArrayList<>(Arrays.asList(1,2,3));
        when(memberGroupsDaoMock.getGroupIds(anyInt())).thenReturn(groupIds);
        when(lineItemDaoMock.getAll(anyInt())).thenReturn(new ArrayList<>());
        when(jdbcTemplateMock.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(shoppingList);
        expectedShoppingList.setLineItems(new ArrayList<>());

        assertEquals(expectedShoppingList, shoppingListDao.getLatest(1));
    }

    @Test
    void testSaveNewShoppingListAddRecordToTable() {
        String query = "INSERT INTO shopping_list(associated_shop_id, archived, member_id, group_id)VALUES (-1,FALSE,-1,?)";
        int groupId = 1234;
        ShoppingList shoppingList = new ShoppingList(groupId);

        when(jdbcTemplateMock.update(query, groupId)).thenReturn(1);
        shoppingListDao.saveNew(shoppingList);

        verify(jdbcTemplateMock).update(query, groupId);
    }

}