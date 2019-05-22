package com.codecool.advanced_project.service.dao.implementation;

import com.codecool.advanced_project.service.dao.MemberGroupsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberGroupsDaoDb implements MemberGroupsDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberGroupsDaoDb(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Integer> getGroupIds(int userId) {
        String query = "SELECT group_id FROM member_groups WHERE member_id=?";
        List<Integer> groupIds = new ArrayList<>();
        List<Map<String, Object>> queryRows = jdbcTemplate.queryForList(query, userId);
        for (Map<String, Object> queryRow : queryRows) {
            groupIds.add((Integer) queryRow.get("group_id"));
        }
        return groupIds;
    }
}
