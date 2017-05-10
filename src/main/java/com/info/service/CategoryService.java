package com.info.service;

import java.util.List;

import com.info.model.Category;


public interface CategoryService {

	List<Category> getPrimaryCategoryList();
	
	List<Category> getCategoryListByParentCode(String parentCategoryCode);

	Category getCategoryByCode(String categoryCode);
}
