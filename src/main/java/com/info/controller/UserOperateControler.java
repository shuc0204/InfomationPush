package com.info.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.info.model.User;
import com.info.service.UserOperate;

/**
 * UserOperateControler是有关用户操作的相应请求controler
 * 所有的带/user/*都会被拦截到该控制器中
 * 
 * @author Administrator
 *
 */

//spring自动识别为Controller
@Controller
//些Controller的根url
@RequestMapping("/user")
public class UserOperateControler {
	
	//controller中注入service
	@Resource UserOperate userOperateImpl;
	
	//controller子url进入 用户登录相关操作
	@RequestMapping("/userLogin")
	//根据url请求到此方法中，实现用户登验证
	public ModelAndView userLogin(User user,HttpSession httpSession){
		ModelAndView mv = null;
		//查询页面传入后台的user在数据库中是否存在
		User loginUser = userOperateImpl.userLogin(user);
		
		//通过loginUser判断是不是存在，页面上输入的密码与数据库中user是否一致
		if (loginUser != null && loginUser.getPassword().equals(user.getPassword())) {			
			
			//如果用户存在且密码输入正确，进行页面的跳转到另一个controoler,也就是进入主页面					
			mv = new ModelAndView("redirect:../meun/getPrimMenu.do");
			//将登录用户进行保存在session中
			httpSession.setAttribute("loginUser", loginUser);
		
		} else {		
			mv = new ModelAndView();
			//当用户不存在，或者密码输入出错，重新返回登录页面
			mv.addObject("msg", "用户不存在或者密码错误！");
			mv.setViewName("../login");			
		}
		return mv;
	}
	
	//controller子url进入 用户注册相关操作
	@RequestMapping("/userRegist")
	public ModelAndView userRegist(User user,String password2,HttpSession httpSession){
		
		ModelAndView mv=new ModelAndView();
		//通过页面传入的值进行相应的验证
		if(user.getUsername().equals("")||user.getPassword().equals("")||user.getName().equals("")||password2.equals("")){	
			mv.addObject("msg", "用户名、密码、昵称都不能为空！");
			mv.setViewName("userregist");
			return mv;
		}
		if(!user.getPassword().equals(password2)){
			mv.addObject("msg", "两次输入密码不一致，请重试！");
			mv.setViewName("userregist");
			return mv;
		}
		
		//传入的信息都正确后进行注册，在注册的service中会进行，是否存在验证
		boolean flag = userOperateImpl.userRegist(user);
		
		//通过返回值判断注册到数据库是否成功
		if(flag){
			mv.addObject("loginUser", user.getName());
			httpSession.setAttribute("loginUser", user);
			mv.setViewName("redirect:../meun/getPrimMenu.do");
		}else{
			mv.addObject("msg", "用户名已经存在，注册失败！");
			mv.setViewName("userregist");
		}
		return mv;
	}
	
	//controller子url进入 用户密码修改相关操作
	@RequestMapping("/userUpdate")
	public ModelAndView userUpdate(User user,String password1,String password2){
		ModelAndView mv=new ModelAndView();
		//密码 相关验证
		if(!(userOperateImpl.getUser(user))){
			mv.addObject("msg", "原密码输入不正确，请重试！");
			mv.setViewName("userUpdate");
			return mv;
		}
		if(!password1.equals(password2)){
			mv.addObject("msg", "两次输入密码不一致，请重试！");
			mv.setViewName("userUpdate");
			return mv;
		}
		user.setPassword(password1);
		
		//执行 修改到数据库操作		
		boolean flag=userOperateImpl.userUpdate(user);
		
		//能过返回值 判断 修改成功与否
		if(flag){
			mv.addObject("msg", "用户信息已经更新，请重新登录！");
			mv.setViewName("../login");
			return mv;	
		}else{
			mv.addObject("msg", "修改失败，请重试！");
			mv.setViewName("userUpdate");
			return mv;	
		}					
	}

}
