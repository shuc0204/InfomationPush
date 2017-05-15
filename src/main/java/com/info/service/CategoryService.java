package com.info.service;

import java.util.List;

import com.info.model.Category;

/**
 * 获取菜单 中的类别 service接口
 * @author Administrator
 *
 */
public interface CategoryService {

	/**
	 * 获取一级分类
	 * @return 一级分类的列表
	 */
	List<Category> getPrimaryCategoryList();

	/**
	 * 获取某一个分类的字分类
	 * @param parentCategoryCode
	 * @return
	 */
	List<Category> getCategoryListByParentCode(String parentCategoryCode);

	/**
	 * 通过分类编码 获取 分类对象
	 * @param categoryCode 分类的编码
	 * @return 分类对象
	 */
	Category getCategoryByCode(String categoryCode);
}
