package com.info.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.User;
import com.info.service.UserOperate;

@Controller
@RequestMapping("/user")
public class UserOperateControler {

	@Resource UserOperate userOperateImpl;
	
	@RequestMapping("/userLogin")
	public ModelAndView userLogin(User user,HttpSession httpSession){
		ModelAndView mv = null;
		User loginUser = userOperateImpl.userLogin(user);
		if (loginUser != null && loginUser.getPassword().equals(user.getPassword())) {			
			mv = new ModelAndView("redirect:../meun/getPrimMenu.do");			
			//mv.addObject("loginUser", loginUser.getName());
			httpSession.setAttribute("loginUser", loginUser);
		} else {
			mv = new ModelAndView();
			mv.addObject("msg", "�û������ڻ����������");
			mv.setViewName("../login");			
		}
		return mv;
	}
	
	@RequestMapping("/userRegist")
	public ModelAndView userRegist(User user,String password2,HttpSession httpSession){
		
		ModelAndView mv=new ModelAndView();

		if(user.getUsername().equals("")||user.getPassword().equals("")||user.getName().equals("")||password2.equals("")){	
			mv.addObject("msg", "�û��������롢�ǳƶ�����Ϊ�գ�");
			mv.setViewName("userregist");
			return mv;
		}
		if(!user.getPassword().equals(password2)){
			mv.addObject("msg", "�����������벻һ�£������ԣ�");
			mv.setViewName("userregist");
			return mv;
		}
		boolean flag = userOperateImpl.userRegist(user);
		if(flag){
			mv.addObject("loginUser", user.getName());
			httpSession.setAttribute("loginUser", user);
			mv.setViewName("redirect:../meun/getPrimMenu.do");
		}else{
			mv.addObject("msg", "�û����Ѿ����ڣ�ע��ʧ�ܣ�");
			mv.setViewName("userregist");
		}
		return mv;
	}
	
	@RequestMapping("/userUpdate")
	public ModelAndView userUpdate(User user,String password1,String password2){
		ModelAndView mv=new ModelAndView();
		if(!(userOperateImpl.getUser(user))){
			mv.addObject("msg", "ԭ�������벻��ȷ�������ԣ�");
			mv.setViewName("userUpdate");
			return mv;
		}
		if(!password1.equals(password2)){
			mv.addObject("msg", "�����������벻һ�£������ԣ�");
			mv.setViewName("userUpdate");
			return mv;
		}
		user.setPassword(password1);
		boolean flag=userOperateImpl.userUpdate(user);
		if(flag){
			mv.addObject("msg", "�û���Ϣ�Ѿ����£������µ�¼��");
			mv.setViewName("../login");
			return mv;	
		}else{
			mv.addObject("msg", "�޸�ʧ�ܣ������ԣ�");
			mv.setViewName("userUpdate");
			return mv;	
		}					
	}

}
