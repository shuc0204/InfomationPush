package com.info.dao;

import java.util.List;

import com.info.model.Keyword;
import com.info.model.KeywordKey;

/**
 * 关键字相关操作实现Dao
 * 是一个接口类，这是Mabatis的特点
 * 由接口去操作*mapping.xml实现对数据库的操作
 * @author Administrator
 *
 */
public interface KeywordMapper {
	
    int deleteByPrimaryKey(KeywordKey key);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    Keyword selectByPrimaryKey(KeywordKey key);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);
    
    int insertKeywordsList(List<Keyword> keys);

	List<Keyword> selectByKeycount(Integer uid);
	
	int deleteOverdueKeyword(Integer minutes);
}