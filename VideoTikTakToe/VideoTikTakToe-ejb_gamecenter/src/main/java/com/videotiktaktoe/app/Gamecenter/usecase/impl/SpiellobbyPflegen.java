package com.videotiktaktoe.app.Gamecenter.usecase.impl;

import javax.inject.Inject;

import com.videotiktaktoe.app.Gamecenter.dao.LobbyDAO;
import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyPflegen;
import com.videotiktaktoe.app.Spielerverwaltung.dao.UserDAO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.User;

public class SpiellobbyPflegen implements ISpiellobbyPflegen{

	@Inject
	LobbyDAO lobbyDAO;
	
	@Inject
	UserDAO userDAO;
	
	@Override
	public void spiellobbyErstellen(LobbyTO aLobby, String userName) {
		System.out.println("Code beim erstellen TO: "+aLobby.getLobbyCode());
		System.out.println("Code beim erstellen: "+aLobby.toLobby().getLobbyCode());
		lobbyDAO.save(aLobby.toLobby());
		User aUser = userDAO.findUserByName(userName);
		aUser.setAdmin(true);
		userDAO.update(aUser);
	}

}
