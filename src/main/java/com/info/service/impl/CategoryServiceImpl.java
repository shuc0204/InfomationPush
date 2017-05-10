package com.info.service.impl;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
	File local_file = FileUtils.getFile("categorydata.json");
	
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
			String body ="";
			String categoryUrl  = "http://piccache.cnki.net/kns/script/min/Json_Category.min.js?v=D59787997F3B8FCE";
			try {
				
				URL url = new URL(categoryUrl);
				URLConnection openConnection = url.openConnection();			
				
				body = convertStreamToString(openConnection.getInputStream());
				//System.out.println(body);
				body = body.substring(body.indexOf('=')+1, body.lastIndexOf(';'));


				if(!local_file.exists()){
					IOUtils.write(body,new FileOutputStream(local_file));
				}

			} catch (IOException e) {
				try {
					body = FileUtils.readFileToString(local_file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
//				e.printStackTrace();
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


	public static void main(String[] args) {
		List<Category> c=new CategoryServiceImpl().getCategoryListByParentCode("A");
		for (Category category : c) {
			System.out.println(category.toString());
		}
	}
	
}
