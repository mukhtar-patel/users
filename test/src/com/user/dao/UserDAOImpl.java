package com.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.user.model.User;


@Repository("userDAO")
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO
{
	static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();

	/**
	 * Fetches all the user from the User table
	 */
	@Override
	public List<User> getAllUsers()
	{
		Session session = getSession();
		List<User> userList = session.createQuery("from User u order by u.foreName").list();
		return userList;
	}

	/**
	 * Fetches user based on the primary key userId
	 */
	@Override
	public User findById(Integer id)
	{
		return (User) getSession().get(User.class, id);
	}

	/**
	 * Inserts a new record in the user table.
	 */
	@Override
	public User insert(User user)
	{
		Integer id = persist(user);
		user.setUserId(id);
		return user;
	}

	/**
	 * Loads the user using the primary key and then removes it from the database.
	 */
	@Override
	public boolean remove(Integer id)
	{
		boolean deleted = false;
		User delUser = findById(id);
		if (delUser != null)
		{
			delete(delUser);
			deleted = true;
		}
		return deleted;
	}
}