package com.info.dao;

import com.info.model.User;
/**
 * 用户相关操作数据库的DAO
 * 是一个接口，操作UserMapping.xml实现对数据库的操作（增删改查）
 * @author Administrator
 *
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);
    
    User selectByUsername(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}