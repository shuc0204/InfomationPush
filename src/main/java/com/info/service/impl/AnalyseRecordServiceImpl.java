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
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.kerberos.KerberosKey;

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
//        System.out.println(user.getName() + " 浏览了 "+ articleService.getArticleByCode(articleCode).getTitle()
//        );
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
        List keyList=getMaxKeywordCount();
        return Arrays.asList(new String[]{
            "文章列表.。。"
        });
    }
    
    
    //获取  最多数目的关键字
	private List getMaxKeywordCount() {
		
		return null;
	}

	@Override
	public List<Article> searchArticleByKeyword(List keywords, int queryCount) {
		// TODO 通过关键字 搜索指定 数目 文章



		return null;
	}
}
