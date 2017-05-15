package com.info.service;

import com.info.model.Article;
import com.info.model.ArticleResultList;

import java.util.List;

/**
 * 通过关键字搜索文章结果集service接口
 */

public interface ArticleSearchService {

    ArticleResultList queryByCategoryCode(String categoryCode);
    ArticleResultList queryByKeyWords(List<String> keyWordList);
    ArticleResultList queryByKeyWords(List<String> keyWordList,int paggSize,int curPage);
    List<Article> searchArticleByKeyWord(List keywords, int queryCount);
}
