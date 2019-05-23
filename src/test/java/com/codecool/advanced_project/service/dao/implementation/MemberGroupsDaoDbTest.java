package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.service.dao.MemberGroupsDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MemberGroupsDaoDbTest {
    private static MemberGroupsDao memberGroupsDao;
    private static JdbcTemplate jdbcTemplateMock;

    @BeforeAll
    static void setUp() {
        jdbcTemplateMock = mock(JdbcTemplate.class);
        memberGroupsDao = new MemberGroupsDaoDb(jdbcTemplateMock);
    }

    @Test
    void getGroupIdsForInvalidUserId() {
        List<Map<String, Object>> emptyQueryList = new ArrayList<>();
        when(jdbcTemplateMock.queryForList(anyString(), anyInt())).thenReturn(emptyQueryList);

        assertEquals(new ArrayList<>(), memberGroupsDao.getGroupIds(1));
    }

    @Test
    void getGroupIdsForValidUserId() {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        List<Map<String, Object>> queryMapList = new ArrayList<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("group_id", 1);
        queryMapList.add(queryMap);
        when(jdbcTemplateMock.queryForList(anyString(), anyInt())).thenReturn(queryMapList);

        assertEquals(result, memberGroupsDao.getGroupIds(1));
    }
}