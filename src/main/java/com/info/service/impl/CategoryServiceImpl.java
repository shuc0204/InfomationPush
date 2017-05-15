package com.info.service.impl;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.info.util.FileCacheUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.info.model.Category;
import com.info.service.CategoryService;

@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{

	static private JSONArray AllCategoryObject;
	static final String tempFileName = "categorydata.json";
	
	public JSONArray getAllCategoryObject(){
		if(AllCategoryObject==null){
			String body ="";
			String categoryUrl  = "http://piccache.cnki.net/kns/script/min/Json_Category.min.js?v=D59787997F3B8FCE";
			try {
				body = IOUtils.toString(new URL(categoryUrl), Charset.forName("utf-8"));
				body = body.substring(body.indexOf('=')+1, body.lastIndexOf(';'));

				File local_file = FileCacheUtil.getFile(tempFileName);
				FileCacheUtil.writeTempCacheOnce(tempFileName,body);

			} catch (IOException e) {
				body = FileCacheUtil.getTempContent(tempFileName);
			}
			AllCategoryObject = (JSONArray) JSONArray.parse(body);
		}
		return AllCategoryObject;
		
	}	
	
	List<Category> getCategoryListFromJsonArray(JSONArray allCategoryObject2){
		
		List<Category> categories = new ArrayList<Category>();
		for (Object object : allCategoryObject2) {
			if(object instanceof JSONObject){
				JSONObject jsonObject = (JSONObject) object;
				Category category= new Category();
				category.setCode(jsonObject.getString("code"));
				category.setTitle(jsonObject.getString("name"));
				categories.add(category);
			}
		}			 		 
		return categories;
	}
	
	@Override
	public List<Category> getPrimaryCategoryList() {
		
		JSONArray allCategoryObject2 = getAllCategoryObject();
		return getCategoryListFromJsonArray(allCategoryObject2);
		
	}
	
	public JSONArray getCategoryListByParentCode(JSONArray allCategoryObject2,String parentCategoryCode) {
		
		JSONArray eval = (JSONArray) JSONPath.eval(allCategoryObject2, "[code='"+parentCategoryCode+"'].child");
		if(eval==null) return new JSONArray();
		if(eval.size()==0){
			for (Object object : allCategoryObject2) {
				if(object instanceof JSONObject){
					JSONObject jsonObject = (JSONObject) object;
					eval = getCategoryListByParentCode( jsonObject.getJSONArray("child"), parentCategoryCode);
					if(eval.size()!=0){
						break;
					}
				}
			}
		}
		return eval;
	}

	@Override
	public List<Category> getCategoryListByParentCode(String parentCategoryCode) {
		JSONArray allCategoryObject2 = getAllCategoryObject();
		JSONArray categorys =  getCategoryListByParentCode(allCategoryObject2,parentCategoryCode);
		return getCategoryListFromJsonArray(categorys);
		
	}

	@Override
	public Category getCategoryByCode(String categoryCode) {
		JSONArray allCategoryObject2 = getAllCategoryObject();
		JSONArray categoryByCodes = getCategoryByCodes(allCategoryObject2, categoryCode);

		if(categoryByCodes.size()>0){
			JSONObject jsonObject = categoryByCodes.getJSONObject(0);
			Category category= new Category();
			category.setCode(jsonObject.getString("code"));
			category.setTitle(jsonObject.getString("name"));
			return  category;
		}
		return null;
	}


	public JSONArray getCategoryByCodes(JSONArray allCategoryObject,String categoryCode) {

		JSONArray eval = (JSONArray) JSONPath.eval(allCategoryObject, "[code='"+categoryCode+"']");
		if(eval==null) {
			return  new JSONArray();
		}
		if(eval.size()==0){
			for (Object object : allCategoryObject) {
				if(object instanceof JSONObject){
					JSONObject jsonObject = (JSONObject) object;
					eval = getCategoryByCodes( jsonObject.getJSONArray("child"), categoryCode);
					if(eval.size()!=0){
						break;
					}
				}
			}
		}
		return eval;
	}
	
}
