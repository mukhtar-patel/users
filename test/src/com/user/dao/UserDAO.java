package com.user.dao;

import java.util.List;

import com.user.model.User;

public interface UserDAO {

	public List<User> getAllUsers();
	
	public User findById(Integer id);
	
	public User insert(User user);
	
	public boolean remove(Integer id);
	
	public User update(User user);
}
