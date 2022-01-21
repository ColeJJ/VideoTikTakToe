package com.videotiktaktoe.app.Gamecenter.dao;

import java.util.HashMap;
import java.util.Map;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Spielsession;

public class SpielsessionDAO extends GenericDAO<Spielsession>{
	
	public SpielsessionDAO() {
		super(Spielsession.class);
	}

	public Spielsession findSessionByLobbyID(int lobbyID) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("lobbyID", lobbyID);
		Spielsession aSession = super.findOneResult(Spielsession.FIND_BY_LOBBYID, parameters);
		
		return aSession != null ? aSession : null;
	}
	
	public boolean deleteSession(int sessionID) {
		return this.delete(sessionID, Spielsession.class);
	}
}
