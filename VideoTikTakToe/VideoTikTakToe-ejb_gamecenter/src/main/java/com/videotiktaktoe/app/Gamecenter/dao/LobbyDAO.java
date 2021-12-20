package com.videotiktaktoe.app.Gamecenter.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;

@Stateless
public class LobbyDAO extends GenericDAO<Lobby> {
	
	public LobbyDAO(){
		super(Lobby.class);
	}
	
	public Lobby findLobbyByName(String lobbyName) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		System.out.println("LobbyName: "+lobbyName);
		parameters.put("lobbyName", lobbyName);
		
		return super.findOneResult(Lobby.FIND_BY_LOBBYNAME, parameters);
	}
}
