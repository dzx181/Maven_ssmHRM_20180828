package com.dzx.dao;

import java.util.List;

import com.dzx.domain.User;


public interface UserDao {
	public List<User> findUserByLogin(User user);
	public int addUser(User user);
	public int deleteUser(int caption);
	public int updateUser(User user);
	public User findUserById(int userId);
	public List<User> selectUser(User user);
	public List<User> findLoginname(User user);
	public int selectUserCount(User user);
	public List<User> selectAllUser(User user);
}
