package com.info.model;

/**
 * ����Ϊ�ؼ���ʵ����Ļ�����
 * @author Administrator
 *
 */
public class KeywordKey {
   
	//�ؼ�����
	private String keyword;
	//�ؼ������� �û�
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