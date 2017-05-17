package com.info.service;

import com.info.model.Article;
import com.info.model.ArticleResultList;

/**
 * ���»�ȡservice�ӿ�
 * @author Administrator
 *
 */
public interface ArticleService {


	/**
	 * ͨ������ı����ȡ�����б�
	 * @param categoryCode �������
	 * @param curPage ��ҳ����
	 * @param pageSize ҳ���С
	 * @return ����ҳ��Ϣ�������б�
	 */
	ArticleResultList getArticleByCategoryCode(String categoryCode, Integer curPage, Integer pageSize);

	/**
	 * ͨ�����²�����ȡ���¶���
	 * @param dbName  dbName
	 * @param dbCode dbCode
	 * @param fileName fileName
	 * @return ���¶���
	 */
	Article getArticleByCode(String dbName,String dbCode,String fileName);

	/**
	 * ��ȡĳһƪ���µĹؼ���
	 * @param article  ���¶���
	 * @return �ؼ������� list
	 */
	String[] getKeyWords(Article article);

}
