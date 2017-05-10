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

@Controller
@RequestMapping("/meun")
public class LoadTreeMenu {

	 @Resource
	  private CategoryService categoryServiceImpl;
	 
	  @RequestMapping("/getPrimMenu")
	  public ModelAndView getPrimMenu(){
		
		ModelAndView mv=new ModelAndView();
	    List<Category> primMenuList = categoryServiceImpl.getPrimaryCategoryList();
	    
	    mv.addObject("primMenuList", primMenuList);
	    mv.setViewName("index");
	    
	    return mv;
	  }
	  
	  @ResponseBody
	  @RequestMapping("/getChildMenu")
	  public  Map getChildMenu(@RequestParam(value="code") String code){
		 
		 List<Category> childMenuList = categoryServiceImpl.getCategoryListByParentCode(code);

		 Map model = new HashMap();
		 model.put("childMenuList", childMenuList);
		 
		 return model;
	  }
	  
	  
}
