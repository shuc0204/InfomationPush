package com.info.service;

import com.info.model.Article;
import com.info.model.ArticleResultList;

import java.util.List;

/**
 * 通过关键字搜索文章结果集service接口
 */

public interface ArticleSearchService {


    /**
     * 通过关键字搜索文章列表  指定要获取的数量
     * @param keywords 关键字数组
     * @param queryCount 要获取多少条数据
     * @return 查询结果的文章列表
     */
    List<Article> searchArticleByKeyWord(List keywords, int queryCount);

    /**
     * 通过关键字获取文章列表  指定分页参数方式获取
     * @param keyWordList  关键字数组
     * @param paggSize 页大小
     * @param curPage 当前页数
     * @return 查询结果的文章列表 带分页信息的对象
     */
    ArticleResultList queryByKeyWords(List<String> keyWordList,int paggSize,int curPage);
}
