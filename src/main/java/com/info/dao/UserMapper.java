package com.info.dao;

import com.info.model.User;
/**
 * �û���ز������ݿ��DAO
 * ��һ���ӿڣ�����UserMapping.xmlʵ�ֶ����ݿ�Ĳ�������ɾ�Ĳ飩
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