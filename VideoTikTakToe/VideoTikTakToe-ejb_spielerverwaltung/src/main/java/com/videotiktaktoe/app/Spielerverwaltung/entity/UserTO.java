package com.videotiktaktoe.app.Spielerverwaltung.entity;

import java.io.Serializable;

import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.User;

public class UserTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2373090897092435656L;
	
	//Variablen
	private int id;
	private String username;
	private String password;
	private String eMailAddress;
	private boolean admin;
	
	
	public UserTO() {
	}
	
	public User toUser() {
		
		User aUser = new User(
				this.getId(),
				this.getUsername(),
				this.getPassword(),
				this.geteMailAddress(),
				this.isAdmin());
		return aUser;
	}
	
	public String toString() {
		return this.getId() + ", " + this.getUsername();
	}
	
	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String geteMailAddress() {
		return eMailAddress;
	}

	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
