package com.info.model;

import com.alibaba.fastjson.JSONObject;
/**
 * Article为文章基本类
 * @author Administrator
 *
 */
public class Article {
	//文章标题
	private String title;
	//文章链接地址
	private String url;
	//文章在网络上所属文件名
	private String fileName;
	//文章在网络上所属数据库编号
	private String dbCode ;
	//文章在网络上所属数据库名
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
