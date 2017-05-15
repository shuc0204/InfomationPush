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

/**
 * 文章分类信息的相关服务
 */
@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{

	/**
	 * 文章所有类别对象
     * 详情请参考  http://piccache.cnki.net/kns/script/min/Json_Category.min.js?v=D59787997F3B8FCE
     * 其实就是一个json数据
	 */
	static private JSONArray AllCategoryObject;
    /**
     * 可以将文件缓存到本地 ，不用每次都去爬
     */
	static final String tempFileName = "categorydata.json";

    /**
     * 从网上把所有分类信息爬下来
     * @return 分类信息的JSON数据
     */
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

    /**
     * 把json数据变成java对象 并返回
     * @param allCategoryObject2
     * @return 某一级的分类列表
     */
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

    /**
     * 通过分类的编号获取分类对象json数据  通过递归
     * @param allCategoryObject
     * @param categoryCode
     * @return
     */
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

    /**
     * 根据上一级分类的编码 获取该分类的字分类  被递归调用
     * @param allCategoryObject2 分类对象的数据
     * @param parentCategoryCode 父分类的编码
     * @return 子分类的 数据 JSON数组
     */
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


	
}
