package com.info.service;

import com.info.model.Article;
import com.info.model.User;

import java.util.List;

/**
 * Created by wayne on 2017/5/10.
 */
public interface AnalyseRecordService {
    
    List<Article> searchArticleByKeyword(List keywords,int queryCount);

	int analyseKeywords(User user, Article article);

	List getSerarchKeywordList(User user);

}
