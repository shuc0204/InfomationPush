package com.info.service;

import java.util.List;

import com.info.model.Category;

/**
 * 获取菜单 中类别service接口
 * @author Administrator
 *
 */
public interface CategoryService {

	List<Category> getPrimaryCategoryList();
	
	List<Category> getCategoryListByParentCode(String parentCategoryCode);

	Category getCategoryByCode(String categoryCode);
}
