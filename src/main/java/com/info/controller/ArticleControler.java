package com.info.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.info.model.ArticleResultList;
import com.info.service.ArticleService;

/**
 * ��ȡ���µ�controller�������󵽴˿�������
 * ������Ӧ�������б���ǰ�˽��������б��չʾ
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleControler {

	//springע��service
	@Resource
	private ArticleService articleServiceImpl;
	

	@RequestMapping("/getArticleList")
	public  ModelAndView getArticleList(@RequestParam(value="code") String code,@RequestParam(value="current") String current,@RequestParam(value="pageSize") String pageSize){
		 
		 ModelAndView mv =new ModelAndView();
		 //����serviceʵ�����У�ͨ�����Ž������»�ȡ������һ�����½����List
		 ArticleResultList articleList = articleServiceImpl.getArticleByCategoryCode(code,Integer.parseInt(current),Integer.parseInt(pageSize));
		 
		 //�����ݱ�����ModelAndView�н��з���
		 mv.addObject("parentCode", code);
		 mv.addObject("articleList",articleList);
		 //���÷��ص�ҳ������
		 mv.setViewName("listArticle");
		 return mv;
	 }
}
