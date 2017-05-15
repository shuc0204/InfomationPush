package com.info.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.Category;
import com.info.model.User;
import com.info.service.CategoryService;

/**
 * LoadTreeMenuΪ���ز˵��Ŀ�������
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/meun")
public class LoadTreeMenu {
	  //ע��service
	  @Resource
	  private CategoryService categoryServiceImpl;
	 
	  //controller��url��ʵ�ֻ�ȡ���˵�����
	  @RequestMapping("/getPrimMenu")
	  public ModelAndView getPrimMenu(){
		
		ModelAndView mv=new ModelAndView();
		//����serviceʵ�����У���ȡ���˵�����������һ�����List
	    List<Category> primMenuList = categoryServiceImpl.getPrimaryCategoryList();
	    //�����List������ModelAndViewk �н��з���ǰ��
	    mv.addObject("primMenuList", primMenuList);
	    //���ص�ǰ��ҳ������
	    mv.setViewName("index");
	    
	    return mv;
	  }
	  
	//controller��url��ʵ�ֻ�ȡ�Ӳ˵�����
	  @ResponseBody
	  @RequestMapping("/getChildMenu")
	  public  Map getChildMenu(@RequestParam(value="code") String code){
		//����serviceʵ�����У���ȡ�Ӳ˵�����������һ�����List
		 List<Category> childMenuList = categoryServiceImpl.getCategoryListByParentCode(code);
		 
		 //���ݷ���ǰ�ˣ���Ϊ�˴�Ϊ���˵�չ���������������Բ���Ҫ����ҳ�����ת��ֻҪ�������ݼ���
		 Map model = new HashMap();
		 model.put("childMenuList", childMenuList);		 
		 return model;
	  }
	  
	  
}
