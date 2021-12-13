package com.videotiktaktoe.app.Gamecenter.usecase.impl;

import com.videotiktaktoe.app.Gamecenter.dao.LobbyDAO;
import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyPflegen;


public class SpiellobbyPflegen implements ISpiellobbyPflegen{
	
	private LobbyDAO lobbyDAO;

	@Override
	public void lobbyErstellen(Lobby aLobby) {
		lobbyDAO.save(aLobby);
	}
	
	

}
