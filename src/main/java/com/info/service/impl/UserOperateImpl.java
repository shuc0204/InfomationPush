package com.info.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.info.dao.UserMapper;
import com.info.model.User;
import com.info.service.UserOperate;

@Service("userOperateImpl")
public class UserOperateImpl implements UserOperate {

	@Resource UserMapper userDao;
	@Override
	public User userLogin(User user) {		
		return userDao.selectByUsername(user.getUsername());
	}

	@Override
	public boolean userRegist(User user) {

		User getUser = userDao.selectByUsername(user.getUsername());
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

	@Override
	public boolean userUpdate(User user) {

		int count=userDao.updateByPrimaryKeySelective(user);

		if(count>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean getUser(User user) {
		// TODO Auto-generated method stub
		User oldUser=userDao.selectByUsername(user.getUsername());
		if(user.getPassword().equals(oldUser.getPassword())){
			return true;
		}else{
			return false;
		}
		
	}

}
