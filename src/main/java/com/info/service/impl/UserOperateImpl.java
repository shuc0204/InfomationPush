package com.info.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.info.dao.UserMapper;
import com.info.model.User;
import com.info.service.UserOperate;

//��������һ��service 
@Service("userOperateImpl")
public class UserOperateImpl implements UserOperate {

	//ע���û�DAO
	@Resource UserMapper userDao;
	
	//�û���¼������ͨ��ҳ�洫�������˺ţ���ѯ�����ݿ����Ƿ����
	@Override
	public User userLogin(User user) {		
		return userDao.selectByUsername(user);
	}
	
	//�û�ע�᷽��
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
	//�û������뷽��
	@Override
	public boolean userUpdate(User user) {

		int count=userDao.updateByPrimaryKeySelective(user);

		if(count>0){
			return true;
		}else{
			return false;
		}
		
	}
	//��ȡ�û���Ϣ�ķ���
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
