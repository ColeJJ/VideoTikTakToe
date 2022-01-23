package com.videotiktaktoe.app.Spielerverwaltung.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;

@Entity
@Table(name = "VTTT_user")
@NamedQueries({
	@NamedQuery(name="User.findUserByName", query="select u from User u where u.username = :username"),
	@NamedQuery(name="User.findUserByID", query="select u from User u where u.id = :id"),
	@NamedQuery(name="User.findByLobbyID", query="select u from User u where u.lobbyID = :lobbyID")
})
public class User {
	
	public static final String FIND_BY_NAME = "User.findUserByName";
	public static final String FIND_BY_ID = "User.findUserByID";
	public static final String FIND_BY_LOBBYID = "User.findByLobbyID";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Variables
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String eMailAddress;
	private boolean admin;
	@Column(nullable = false)
	private String usergroup;
	private int lobbyID;
	
	
	//Konstruktor
	public User() {}
	
	public User(int id, String username, String password, String eMailAddress, boolean admin, String usergroup, int lobbyID) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.eMailAddress = eMailAddress;
		this.admin = admin;
		this.usergroup = usergroup;
		this.lobbyID = lobbyID;
	}
	
	public User(String username, String password, String eMailAddress, boolean admin, String usergroup, int lobbyID) {
		this.username = username;
		this.password = password;
		this.eMailAddress = eMailAddress;
		this.admin = admin;
		this.usergroup = usergroup;
		this.lobbyID = lobbyID;
	}
	
	public UserTO toUserTO() {	
		UserTO aUserTO = new UserTO();
		aUserTO.setId(this.getId());
		aUserTO.setUsername(this.getUsername());
		aUserTO.setPassword(this.getPassword());
		aUserTO.seteMailAddress(this.geteMailAddress());
		aUserTO.setAdmin(this.isAdmin());
		aUserTO.setUsergroup(this.getUsergroup());
		aUserTO.setLobbyID(this.getLobbyID());
		return aUserTO;	
	}
	
	public String toString() {
		return "ID: " + this.getId() +", Username: " + this.getUsername() + ", Password: " + this.getPassword() + ", Mail: " + this.geteMailAddress() + ", Admin: " + this.isAdmin();
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
	
	public String getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}

	public int getLobbyID() {
		return lobbyID;
	}

	public void setLobbyID(int lobbyID) {
		this.lobbyID = lobbyID;
	}
}
