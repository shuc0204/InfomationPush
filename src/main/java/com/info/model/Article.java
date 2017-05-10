package com.info.model;

import com.alibaba.fastjson.JSONObject;

public class Article {
	private String title;
	private String url;
	private String code;
	
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
