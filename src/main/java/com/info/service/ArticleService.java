package com.info.service;

import com.info.model.Article;
import com.info.model.ArticleResultList;

/**
 * 文章获取service接口
 * @author Administrator
 *
 */
public interface ArticleService {
	
	ArticleResultList getArticleByCategoryCode(String categoryCode);

	ArticleResultList getArticleByCategoryCode(String categoryCode, Integer curPage, Integer pageSize);

	Article getArticleByCode(String dbName,String dbCode,String fileName);

	String[] getKeyWords(Article article);

}
