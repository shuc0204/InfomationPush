package com.info.dao;

import java.util.List;

import com.info.model.Keyword;
import com.info.model.KeywordKey;

public interface KeywordMapper {
	
    int deleteByPrimaryKey(KeywordKey key);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    Keyword selectByPrimaryKey(KeywordKey key);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);
    
    int insertKeywordsList(List<Keyword> keys);

	List<Keyword> selectByKeycount(Integer uid);
}