package com.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.dao.UserDAO;
import com.user.model.User;

/**
 * Service class for user operations
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDAO userDAO;

	static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
	
	/**
	 * Lists all users
	 * 
	 * @return
	 */
	@Override
	@Transactional
	public List<User> listUsers()
	{
		return userDAO.getAllUsers();
	}
	

	/**
	 * Get a specific user
	 * 
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public User getUser(Integer id)
	{
		return userDAO.findById(id);
	}

	/**
	 * Create a user
	 * 
	 * @param user
	 * @return
	 */
	@Override
	@Transactional
	public User createUser(User user)
	{                    
		user.setCreated(new Date());
		User u = userDAO.insert(user);
		return u;
	}

	/**
	 * Remove a user.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean removeUser(Integer id)
	{
		return userDAO.remove(id);
	}

	/**
	 * Update a user
	 * 
	 * @param user
	 */
	@Transactional
	public User updateUser(User user)
	{
		//User old = getUser(user.getUserId());
		//user.setCreated(old.getCreated());
		userDAO.update(user);
		return user;
	}
}