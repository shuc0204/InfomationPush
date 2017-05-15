package com.info.service;

import java.util.List;

import com.info.model.Category;

/**
 * ��ȡ�˵� �е���� service�ӿ�
 * @author Administrator
 *
 */
public interface CategoryService {

	/**
	 * ��ȡһ������
	 * @return һ��������б�
	 */
	List<Category> getPrimaryCategoryList();

	/**
	 * ��ȡĳһ��������ַ���
	 * @param parentCategoryCode
	 * @return
	 */
	List<Category> getCategoryListByParentCode(String parentCategoryCode);

	/**
	 * ͨ��������� ��ȡ �������
	 * @param categoryCode ����ı���
	 * @return �������
	 */
	Category getCategoryByCode(String categoryCode);
}
