package com.videotiktaktoe.app.Spielerverwaltung.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.User;

@Stateless
public class UserDAO extends GenericDAO<User>{

	public UserDAO() {
		super(User.class);
	}
	
	public User findUserByName(String username) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username", username);
		
		return super.findOneResult(User.FIND_BY_NAME, parameters);
	}

	public User findUserByID(int userID) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", userID);
		
		return super.findOneResult(User.FIND_BY_ID, parameters);
	}
	
	public List<User> findAllUsersByLobbyID(int lobbyID){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("lobbyID", lobbyID);
		
		return super.findListResult(User.FIND_BY_LOBBYID, parameters);
	}
}
