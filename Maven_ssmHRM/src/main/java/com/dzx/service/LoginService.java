package com.dzx.service;

import java.util.List;

import com.dzx.domain.User;


public interface LoginService {
	public List<User> findUserByLogin(User user);
	
}
