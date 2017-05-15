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
 * LoadTreeMenu为加载菜单的控制器类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/meun")
public class LoadTreeMenu {
	  //注入service
	  @Resource
	  private CategoryService categoryServiceImpl;
	 
	  //controller子url，实现获取主菜单方法
	  @RequestMapping("/getPrimMenu")
	  public ModelAndView getPrimMenu(){
		
		ModelAndView mv=new ModelAndView();
		//调用service实现类中，获取主菜单方法，返回一个类别List
	    List<Category> primMenuList = categoryServiceImpl.getPrimaryCategoryList();
	    //将类别List保存在ModelAndViewk 中进行返回前端
	    mv.addObject("primMenuList", primMenuList);
	    //返回的前端页面名称
	    mv.setViewName("index");
	    
	    return mv;
	  }
	  
	//controller子url，实现获取子菜单方法
	  @ResponseBody
	  @RequestMapping("/getChildMenu")
	  public  Map getChildMenu(@RequestParam(value="code") String code){
		//调用service实现类中，获取子菜单方法，返回一个类别List
		 List<Category> childMenuList = categoryServiceImpl.getCategoryListByParentCode(code);
		 
		 //数据返回前端，因为此处为主菜单展开做出的请求，所以不需要进行页面的跳转，只要返回数据即可
		 Map model = new HashMap();
		 model.put("childMenuList", childMenuList);		 
		 return model;
	  }
	  
	  
}
