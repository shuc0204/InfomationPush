package com.info.dao;

import java.util.List;

import com.info.model.Keyword;

public interface KeywordMapper {
    int deleteByPrimaryKey(Integer kid);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    Keyword selectByPrimaryKey(Integer kid);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);

	int insertKeywordsList(List<Keyword> keys);

	List<Keyword> selectByKeycount();
}