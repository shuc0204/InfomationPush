package com.info.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.info.model.ArticleResultList;
import com.info.service.ArticleService;

/**
 * 获取文章的controller，当请求到此控制器后，
 * 返回相应的文章列表，让前端进行文章列表的展示
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleControler {

	//spring注入service
	@Resource
	private ArticleService articleServiceImpl;
	

	@RequestMapping("/getArticleList")
	public  ModelAndView getArticleList(@RequestParam(value="code") String code,@RequestParam(value="current") String current,@RequestParam(value="pageSize") String pageSize){
		 
		 ModelAndView mv =new ModelAndView();
		 //调用service实现类中，通过类别号进行文章获取，返回一个文章结果集List
		 ArticleResultList articleList = articleServiceImpl.getArticleByCategoryCode(code,Integer.parseInt(current),Integer.parseInt(pageSize));
		 
		 //将数据保存在ModelAndView中进行返回
		 mv.addObject("parentCode", code);
		 mv.addObject("articleList",articleList);
		 //设置返回的页面名称
		 mv.setViewName("listArticle");
		 return mv;
	 }
}
