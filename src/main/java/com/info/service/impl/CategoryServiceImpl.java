package com.info.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.info.model.Category;
import com.info.service.CategoryService;

@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{

	static private JSONArray AllCategoryObject;

	
	public static String convertStreamToString(InputStream is) {      
        /*  
          * To convert the InputStream to String we use the BufferedReader.readLine()  
          * method. We iterate until the BufferedReader return null which means  
          * there's no more data to read. Each line will appended to a StringBuilder  
          * and returned as String.  
          */     
         BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      
         StringBuilder sb = new StringBuilder();      
     
         String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {      
                 sb.append(line + "\n");      
             }      
         } catch (IOException e) {      
             e.printStackTrace();      
         } finally {      
            try {      
                 is.close();      
             } catch (IOException e) {      
                 e.printStackTrace();      
             }      
         }      
     
        return sb.toString();      
     }
	
	public JSONArray getAllCategoryObject(){
		if(AllCategoryObject==null){
			String categoryUrl  = "http://piccache.cnki.net/kns/script/min/Json_Category.min.js?v=D59787997F3B8FCE"; 
			try {
				
				URL url = new URL(categoryUrl);
				URLConnection openConnection = url.openConnection();			
				
				String body = convertStreamToString(openConnection.getInputStream());
				//System.out.println(body);
				body = body.substring(body.indexOf('=')+1, body.lastIndexOf(';'));
				
				AllCategoryObject = (JSONArray) JSONArray.parse(body);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
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
	
	
	public static void main(String[] args) {
		List<Category> c=new CategoryServiceImpl().getCategoryListByParentCode("A");
		for (Category category : c) {
			System.out.println(category.toString());
		}
	}
	
}
