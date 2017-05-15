package com.info.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
/**
 * Category为菜单中所显示的类别 
 * @author Administrator
 *
 */
public class Category {
	
	//类别码 code
	private String code;
	//类别标题
	private String title;

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
