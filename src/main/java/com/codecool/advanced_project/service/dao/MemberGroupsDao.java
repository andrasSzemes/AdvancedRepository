package com.codecool.advanced_project.service.dao;

import java.util.List;

public interface MemberGroupsDao {
    List<Integer> getGroupIds(int userId);
}
