package com.info.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.info.model.User;
import com.info.service.UserOperate;

/**
 * UserOperateControler���й��û���������Ӧ����controler
 * ���еĴ�/user/*���ᱻ���ص��ÿ�������
 * 
 * @author Administrator
 *
 */

//spring�Զ�ʶ��ΪController
@Controller
//ЩController�ĸ�url
@RequestMapping("/user")
public class UserOperateControler {
	
	//controller��ע��service
	@Resource UserOperate userOperateImpl;
	
	//controller��url���� �û���¼��ز���
	@RequestMapping("/userLogin")
	//����url���󵽴˷����У�ʵ���û�����֤
	public ModelAndView userLogin(User user,HttpSession httpSession){
		ModelAndView mv = null;
		//��ѯҳ�洫���̨��user�����ݿ����Ƿ����
		User loginUser = userOperateImpl.userLogin(user);
		
		//ͨ��loginUser�ж��ǲ��Ǵ��ڣ�ҳ������������������ݿ���user�Ƿ�һ��
		if (loginUser != null && loginUser.getPassword().equals(user.getPassword())) {			
			
			//����û�����������������ȷ������ҳ�����ת����һ��controoler,Ҳ���ǽ�����ҳ��					
			mv = new ModelAndView("redirect:../meun/getPrimMenu.do");
			//����¼�û����б�����session��
			httpSession.setAttribute("loginUser", loginUser);
		
		} else {		
			mv = new ModelAndView();
			//���û������ڣ�������������������·��ص�¼ҳ��
			mv.addObject("msg", "�û������ڻ����������");
			mv.setViewName("../login");			
		}
		return mv;
	}
	
	//controller��url���� �û�ע����ز���
	@RequestMapping("/userRegist")
	public ModelAndView userRegist(User user,String password2,HttpSession httpSession){
		
		ModelAndView mv=new ModelAndView();
		//ͨ��ҳ�洫���ֵ������Ӧ����֤
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
		
		//�������Ϣ����ȷ�����ע�ᣬ��ע���service�л���У��Ƿ������֤
		boolean flag = userOperateImpl.userRegist(user);
		
		//ͨ������ֵ�ж�ע�ᵽ���ݿ��Ƿ�ɹ�
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
	
	//controller��url���� �û������޸���ز���
	@RequestMapping("/userUpdate")
	public ModelAndView userUpdate(User user,String password1,String password2){
		ModelAndView mv=new ModelAndView();
		//���� �����֤
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
		
		//ִ�� �޸ĵ����ݿ����		
		boolean flag=userOperateImpl.userUpdate(user);
		
		//�ܹ�����ֵ �ж� �޸ĳɹ����
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
