package com.info.service;

import com.info.model.Article;
import com.info.model.ArticleResultList;

public interface ArticleService {
	
	ArticleResultList getArticleByCategoryCode(String categoryCode);

	ArticleResultList getArticleByCategoryCode(String categoryCode, Integer curPage, Integer pageSize);

	Article getArticleByCode(String articleCode);

	String[] getKeyWords(String articleCode);
}
