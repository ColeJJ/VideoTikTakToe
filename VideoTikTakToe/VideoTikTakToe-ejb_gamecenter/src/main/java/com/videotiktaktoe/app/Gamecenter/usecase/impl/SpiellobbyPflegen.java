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
	public void SpiellobbyErstellen(LobbyTO aLobby, int userID) {
		lobbyDAO.save(aLobby.toLobby());
		User aUser = userDAO.findUserByID(userID);
		aUser.setAdmin(true);
		userDAO.save(aUser);
	}

}
