package com.info.service.impl;

import com.info.dao.KeywordMapper;
import com.info.model.Article;
import com.info.model.Keyword;
import com.info.model.User;
import com.info.service.AnalyseRecordService;
import com.info.service.ArticleSearchService;
import com.info.service.ArticleService;
import com.info.service.CategoryService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 */
@Service
public class AnalyseRecordServiceImpl implements AnalyseRecordService {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private KeywordMapper keywordDao;   

    @Autowired
    ArticleSearchService articleSearchService ;
    
    //通过一串关键字，查询指定数量的文章信息做为推送列表
	@Override
	public List<Article> searchArticleByKeyword(List keywords, int queryCount) {
		return  articleSearchService.searchArticleByKeyWord(keywords,queryCount);
	}
		
	//将指定文章中的关键字，保存到指定 用户的keywrod表中
	@Override
	public int analyseKeywords(User user, Article article) {
		
		//获取 文章关键字数组
    	String[] keyWords=articleService.getKeyWords(article);
            
        List<Keyword> keys=new ArrayList<Keyword>();
        
       	for (String string : keyWords) {
			Keyword key=new Keyword();
			key.setKeyword(string);
			key.setUid(user.getUid());
			Date date=new Date();
			key.setUpdatetime(date.getTime());
			keys.add(key);			
		}
       	
		if(keys.size()==0){
            return 0;
        }
		//保存关键字到数据库中
        int count = keywordDao.insertKeywordsList(keys);    
		return count;
	}


	@Override
	public List getSerarchKeywordList(User user) {
		
		// 数据库 查询 出来关键字次数最多的5个；
        List<Keyword> keyList=keywordDao.selectByKeycount(user.getUid());
      
        List keywords=new ArrayList();
        for (Keyword keyword : keyList) {
			keywords.add(keyword.getKeyword());
		}
        return keywords;
	}
	
}
