package com.info.dao;

import com.info.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);
    
    User selectByUsername(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}