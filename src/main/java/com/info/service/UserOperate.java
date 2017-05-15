package com.info.service;

import com.info.model.User;

/**
 * 用户相关操作service接口
 * @author Administrator
 *
 */
public interface UserOperate {

	User userLogin(User user);
	
	boolean userRegist(User user);
	
	boolean userUpdate(User user);

	boolean getUser(User user);
}
