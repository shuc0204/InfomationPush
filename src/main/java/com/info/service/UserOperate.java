package com.info.service;

import com.info.model.User;

public interface UserOperate {

	User userLogin(User user);
	
	boolean userRegist(User user);
	
	boolean userUpdate(User user);

	boolean getUser(User user);
}
