package com.info.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.info.dao.UserMapper;
import com.info.model.User;
import com.info.service.UserOperate;

//申明这是一个service 
@Service("userOperateImpl")
public class UserOperateImpl implements UserOperate {

	//注入用户DAO
	@Resource UserMapper userDao;
	
	//用户登录方法，通过页面传过来的账号，查询在数据库中是否存在
	@Override
	public User userLogin(User user) {		
		return userDao.selectByUsername(user);
	}
	
	//用户注册方法
	@Override
	public boolean userRegist(User user) {

		User getUser = userDao.selectByUsername(user);
		int count=0;
		if(getUser==null){
			count = userDao.insert(user);
		}		
		if(count>0){
			return	true;	 
		}else{
			return false;
		}		 
	}
	//用户修密码方法
	@Override
	public boolean userUpdate(User user) {

		int count=userDao.updateByPrimaryKeySelective(user);

		if(count>0){
			return true;
		}else{
			return false;
		}
		
	}
	//获取用户信息的方法
	@Override
	public boolean getUser(User user) {
		User oldUser=userDao.selectByUsername(user);
		if(user.getPassword().equals(oldUser.getPassword())){
			return true;
		}else{
			return false;
		}
		
	}

}
