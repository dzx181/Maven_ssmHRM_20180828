package com.dzx.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dzx.domain.User;

public interface UserService {
	public int addUser(User user);

	public int deleteUser(Integer[] caption);

	public int updateUser(User user);

	public User findUserById(int userId);

	public List<User> selectUser(User user);

	public List<User> findLoginname(User user);

	// 封装导出方法
	public Boolean importExcel(String[] thTitle, List<User> listValue, String tableTitle, String path,HttpServletRequest request);

	// 查找所有用户
	public List<User> selectAllUser(User user);
	
	//保存复选框的值
	public Map<Integer,Integer[]> saveCheckbox(Integer[] caption,User user,HttpServletRequest request);
}
