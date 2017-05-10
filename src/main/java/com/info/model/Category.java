package com.info.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class Category {

	private String code;
	private String title;
//	private List<Category> child;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
