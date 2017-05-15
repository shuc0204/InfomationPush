package com.info.model;

import com.alibaba.fastjson.JSONObject;
/**
 * ArticleΪ���»�����
 * @author Administrator
 *
 */
public class Article {
	//���±���
	private String title;
	//�������ӵ�ַ
	private String url;
	//�����������������ļ���
	private String fileName;
	//�������������������ݿ���
	private String dbCode ;
	//�������������������ݿ���
	private  String dbName;
	
	public String getDbCode() {
		return dbCode;
	}

	public void setDbCode(String dbCode) {
		this.dbCode = dbCode;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
