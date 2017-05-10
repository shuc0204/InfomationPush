package com.info.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.Article;
import com.info.model.ArticleResultList;
import com.info.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleControler {


	@Resource
	private ArticleService articleServiceImpl;
	

	@RequestMapping("/getArticleList")
	public  ModelAndView getArticleList(@RequestParam(value="code") String code,@RequestParam(value="current") String current,@RequestParam(value="pageSize") String pageSize){
		 
		 ModelAndView mv =new ModelAndView();
		 //System.out.println(code+"  "+current+"   "+pageSize);
		 ArticleResultList articleList = articleServiceImpl.getArticleByCategoryCode(code,Integer.parseInt(current),Integer.parseInt(pageSize));
		 mv.addObject("parentCode", code);
		 mv.addObject("articleList",articleList);
		 mv.setViewName("listArticle");
		 return mv;
	 }
}
