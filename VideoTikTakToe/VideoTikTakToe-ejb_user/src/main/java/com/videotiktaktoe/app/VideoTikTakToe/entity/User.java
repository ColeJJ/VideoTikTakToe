package com.videotiktaktoe.app.VideoTikTakToe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VTTT_User")
@NamedQuery(name="User.findUserByNickname", query="select u from User u where u.username = :username")
public class User {
	
	public static final String FIND_BY_NAME = "User.findUserByName";
	
	//PK
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Variables
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	private String eMailAddress;
	private boolean admin;
	
	
	//Contructor
	public User() {}
	
	
	//Getters and Setter
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User user = (User) obj;
			return user.username.equals(getUsername());
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return getId();
	}
	
	
	
}
