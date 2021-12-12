package com.videotiktaktoe.app.Gamecenter.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;



@Stateless
public class LobbyDAO extends GenericDAO<Lobby> {
	
	public LobbyDAO(){
		super(Lobby.class);
	}
	
	public void delete(Lobby aLobby){
		super.delete(aLobby.getLobbyID(),Lobby.class);
	}
	
	public List<Lobby> findLobbyByLobbyName(String name){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("vorname", name);
		
		
		return super.findListResult(Lobby.FIND_BY_LOBBYNAME, parameters);
		
	}

}
