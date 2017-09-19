package com.user.controller;

import java.net.URI;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.user.model.User;
import com.user.service.UserService;

/**
 * Users controller
 *
 */
@RestController
@RequestMapping("/users")
public class UsersRestController
{
	static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UsersRestController.class);

	@Autowired
	private UserService userService;

	/**
	 * Returns all users
	 *
	 * @return
	 */
	@RequestMapping(value= "/all", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getUsers()
	{
		ResponseEntity<List<User>> response = null;
		List<User> users = userService.listUsers();
		if (users != null) {
			response = new ResponseEntity<>(users, HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	/**
	 * Deletes a user
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> remove(@PathVariable Integer userId)
	{
		boolean deleted = false;
		ResponseEntity<?> response = null;
		try {
			deleted = userService.removeUser(userId);
		}
		catch (Exception e) {
			logger.error("Error deleting user from database: " + e.getMessage());
		}
		if (deleted)
			response = new ResponseEntity<>(HttpStatus.OK);
		else
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return response;
	}

	/**
	 * Get a specific user
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable Integer userId)
	{
		ResponseEntity<User> response = null;
		User user = userService.getUser(userId);
		if (user != null) {
			response = new ResponseEntity<>(user, HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return response;
	}

	/**
	 * Edits user
	 *
	 * @param input
	 * @return
	 */
	@RequestMapping(value= "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody User input)
	{
		ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.OK);
		logger.info("User Id to be updated is " + input.getUserId());
		try
		{
			userService.updateUser(input);
			response = new ResponseEntity<>(HttpStatus.OK);
		}
		catch (HibernateException he) {
			logger.error("Unable to update user in database: " + he.getMessage());
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			logger.error("Unable to update user in database: " + e.getMessage());
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	/**
	 * Add a user
	 *
	 * @param input
	 * @return
	 */
	@RequestMapping(value= "/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody User input)
	{
		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<?> response = null;
		logger.info("User description is " + input.getEmail());
		try
		{
			User user = userService.createUser(input);
			response = new ResponseEntity<>(user, HttpStatus.OK);
		}
		catch (Exception e)
		{
			logger.error("Unable to insert new user in database: " + e.getMessage());
			response = new ResponseEntity<>(null, httpHeaders, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return response;
	}
}