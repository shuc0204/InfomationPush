package com.info.service;

import com.info.model.Article;
import com.info.model.ArticleResultList;

/**
 * 文章获取service接口
 * @author Administrator
 *
 */
public interface ArticleService {


	/**
	 * 通过分类的编码获取文章列表
	 * @param categoryCode 分类编码
	 * @param curPage 分页参数
	 * @param pageSize 页面大小
	 * @return 带分页信息的文章列表
	 */
	ArticleResultList getArticleByCategoryCode(String categoryCode, Integer curPage, Integer pageSize);

	/**
	 * 通过文章参数获取文章对象
	 * @param dbName  dbName
	 * @param dbCode dbCode
	 * @param fileName fileName
	 * @return 文章对象
	 */
	Article getArticleByCode(String dbName,String dbCode,String fileName);

	/**
	 * 获取某一篇文章的关键词
	 * @param article  文章对象
	 * @return 关键词数据 list
	 */
	String[] getKeyWords(Article article);

}
