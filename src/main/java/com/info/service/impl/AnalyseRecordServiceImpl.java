package com.info.service.impl;

import com.info.model.User;
import com.info.service.AnalyseRecordService;
import com.info.service.ArticleService;
import com.info.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public List analyseAndPush(User user, String articleCode) {
    		
    	//获取 文章关键字数组
    	String[] keyWords=articleService.getKeyWords(articleCode);

        System.out.println(user.getName() + " 浏览了 " 
                    + articleService.getArticleByCode(articleCode).getTitle()
        );

        /**
         *
         *  这里将用户浏览记录保存到数据库
         *
         *
         */


        /**
         *
         * 分析数据库最近记录  获取推荐文章
         *
         *
         */



        return Arrays.asList(new String[]{
            "文章列表.。。"
        });
    }
}
