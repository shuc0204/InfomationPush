package com.info.controller;

import com.info.model.Article;
import com.info.model.ArticleResultList;
import com.info.model.User;
import com.info.service.AnalyseRecordService;
import com.info.service.ArticleService;
import com.info.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wayne on 2017/5/10.
 */
@Controller()
@RequestMapping("/api")
public class AnalyseAndPushController {

    @Autowired
    private AnalyseRecordService analyseRecordService;


    @RequestMapping("/analyse/submit.do")
    @ResponseBody
    public Object analyseAndPush( Article article, HttpSession session){

        Object userObj = session.getAttribute("loginUser");
        if(userObj ==null){
            return "对不起,用户未登录！";
        }
        if(article==null || article.getFileName()==null || article.getDbCode()==null|| article.getDbName() ==null){
            return "数据传输错误！";
        }
        User user = (User) userObj;
        
        int count = analyseRecordService.analyseKeywords(user,article);
        if(count>0){
        	return "插入keyword成功";
        }else{
        	return "数据传输错误！";
        }
        
    }
    
    @RequestMapping("/analyse/push.do")
    @ResponseBody
    public ModelAndView pushArticle( HttpSession session){
    	
    	ModelAndView mv=new ModelAndView();
    	Object userObj = session.getAttribute("loginUser");
        
    	if(userObj ==null){
            return mv;
        }  	
        User user = (User) userObj;
        List keywordList=analyseRecordService.getSerarchKeywordList(user);
    	List<Article> pushArticleList = analyseRecordService.searchArticleByKeyword(keywordList, 20);
        mv.addObject("pushArticleList", pushArticleList);
        mv.setViewName("pushArticle");
        
        return mv;  	
    }

}
