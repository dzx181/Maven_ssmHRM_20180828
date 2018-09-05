package com.dzx.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzx.dao.UserDao;
import com.dzx.domain.User;
import com.dzx.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public List<User> findUserByLogin(User user) {
		// TODO Auto-generated method stub
		return userDao.findUserByLogin(user);
	}

	

}
