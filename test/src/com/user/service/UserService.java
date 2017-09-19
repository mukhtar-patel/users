package com.user.service;

import java.util.List;

import com.user.model.User;

public interface UserService {

	public List<User> listUsers();
	
	public User getUser(Integer id);
	
	public boolean removeUser(Integer id);
	
	public User updateUser(User user);
	
	public User createUser(User user);
}
