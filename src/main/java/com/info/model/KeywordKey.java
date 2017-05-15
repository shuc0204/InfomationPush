package com.info.model;

/**
 * 此类为关键字实体类的基本类
 * @author Administrator
 *
 */
public class KeywordKey {
   
	//关键字名
	private String keyword;
	//关键字所属 用户
    private Integer uid;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}