package com.info.controller;

import com.info.model.Article;
import com.info.model.User;
import com.info.service.AnalyseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * AnalyseAndPushController
 * 是实现文章中关键字存储和文章推送的控制器
 * 
 */

@Controller()
@RequestMapping("/api")
public class AnalyseAndPushController {
	
	//spring注入service
    @Autowired
    private AnalyseRecordService analyseRecordService;

    @RequestMapping("/analyse/submit.do")
    @ResponseBody
    //文章关键字保存方法
    public Object analyseAndPush( Article article, HttpSession session){
    	
    	//判断用户是否登录，从session中获取 ，如果未登录将不进行推送
        Object userObj = session.getAttribute("loginUser");
        if(userObj ==null){
            return "对不起,用户未登录！";
        }
        //判断传入的文章信息是否有误
        if(article==null || article.getFileName()==null || article.getDbCode()==null|| article.getDbName() ==null){
            return "数据传输错误！";
        }
        //此user为登录用户账号
        User user = (User) userObj;      
        //调用service，实现将文章中的关键字保存到当前用户
        int count = analyseRecordService.analyseKeywords(user,article);
        //通过返回的count数量，进行判断是否成功
        if(count>0){
        	return "插入keyword成功";
        }else{
        	return "数据传输错误！";
        }
        
    }
    
    @RequestMapping("/analyse/push.do")
    @ResponseBody
    //推送 相关信息的方法
    public ModelAndView pushArticle( HttpSession session){
    	
    	ModelAndView mv=new ModelAndView();
    	//判断用户是否登录，从session中获取 ，如果未登录将不进行推送
    	Object userObj = session.getAttribute("loginUser");      
    	if(userObj ==null){
            return mv;
        }  	
        User user = (User) userObj;
        //获取指定用户  数据库keyword表中，关键字出现数量最高的5个，做为推送文章的关键字
        List keywordList=analyseRecordService.getSerarchKeywordList(user);
        //调用service中，通过关键字进行搜索文章列表
    	List<Article> pushArticleList = analyseRecordService.searchArticleByKeyword(keywordList, 20);
    	
    	//将数据保存在ModelAndView中进行返回，并设置返回的页面名称
    	mv.addObject("pushArticleList", pushArticleList);
        mv.setViewName("pushArticle");
        
        return mv;  	
    }

}
