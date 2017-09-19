package com.user.model;

import java.util.Date;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User class
 *
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable
{
	private static final long serialVersionUID = -4020422113803740590L;

	@Id
	@Column(name = "user_id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "foreName", nullable = false)
	private String foreName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	


	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "created", nullable = false)
	private Date created;
	
	public User ()
	{
	}
	
	/**
	 * @param userId
	 * @param foreName
	 * @param lastName
	 * @param email
	 * @param created
	 */
	public User(Integer userId, String foreName, String lastName, String email, Date created) {
		super();
		this.userId = userId;
		this.foreName = foreName;
		this.lastName = lastName;
		this.email = email;
		this.created = created;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getForeName() {
		return foreName;
	}


	public void setForeName(String foreName) {
		this.foreName = foreName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}

	
	@JsonIgnore
	public Vector<String> validate(boolean edit)
	{
		Vector<String> errors = new Vector<>();

		if ((foreName == null) || (foreName.length() == 0)) {errors.add("Fore name is a mandatory field");}
		if ((lastName == null) || (lastName.length() == 0)) {errors.add("Last name is a mandatory field");}
		if ((email == null) || (email.length() == 0)) {errors.add("Email is a mandatory field");}
		if (created == null) {errors.add("Created is a mandatory field");}
		
		
		return errors;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 *
	 * @return
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("USERID: ").append(userId).append(",  ");
		sb.append("FORENAME: ").append(foreName).append(",  ");
		sb.append("LASTNAME: ").append(lastName).append(",  ");
		sb.append("EMAIL: ").append(email).append(",  ");
		sb.append("CREATED: ").append(created).append(",  ");
		
		return sb.toString();
	}
}