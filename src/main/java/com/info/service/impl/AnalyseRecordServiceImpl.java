package com.info.service.impl;

import com.info.dao.KeywordMapper;
import com.info.model.Article;
import com.info.model.Keyword;
import com.info.model.User;
import com.info.service.AnalyseRecordService;
import com.info.service.ArticleService;
import com.info.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wayne on 2017/5/10.
 */
@Service
public class AnalyseRecordServiceImpl implements AnalyseRecordService {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private KeywordMapper keywordDao;
    
    @Override
    public List analyseAndPush(User user, String articleCode) {
    		
    	//获取 文章关键字数组
    	String[] keyWords=articleService.getKeyWords(articleCode);
        /**
         *
         *  这里将用户浏览记录保存到数据库     
         */      
       List<Keyword> keys=new ArrayList<Keyword>();
        for (String string : keyWords) {
			Keyword key=new Keyword();
			key.setKeyword(string);
			key.setUid(user.getUid());
			keys.add(key);			
		}
        int count = keywordDao.insertKeywordsList(keys);      
        /**
         *
         * 分析数据库最近记录  获取推荐文章
         * 通过关键字 获取 指定数目 的文章 
         *
         */
        // 数据库 查询 出来关键字次数最多的5个；
        List<Keyword> keyList=keywordDao.selectByKeycount();
      
        List keywords=new ArrayList();
        for (Keyword keyword : keyList) {
			keywords.add(keyword.getKeyword());
		}
        
        return keywords;
    }
         

	@Override
	public List<Article> searchArticleByKeyword(List keywords, int queryCount) {
		// TODO 通过关键字 搜索指定 数目 文章



		return null;
	}
	
}
