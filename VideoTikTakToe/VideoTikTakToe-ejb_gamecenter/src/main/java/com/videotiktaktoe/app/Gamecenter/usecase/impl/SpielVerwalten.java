package com.videotiktaktoe.app.Gamecenter.usecase.impl;

import javax.inject.Inject;

import com.videotiktaktoe.app.Gamecenter.dao.SpielsessionDAO;
import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;
import com.videotiktaktoe.app.Gamecenter.entity.impl.Spielsession;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpielVerwalten;

public class SpielVerwalten implements ISpielVerwalten{

	@Inject
	SpielsessionDAO sessionDAO;
	
	@Override
	public SpielsessionTO spielStarten(int anzahlRunden, int lobbyID) {
		Spielsession aSession = new Spielsession(anzahlRunden, lobbyID);
		sessionDAO.save(aSession);
		SpielsessionTO aSessionTO = sessionDAO.findSessionByLobbyID(lobbyID).toSessionTO();
		
		return aSessionTO;
	}

	@Override
	public SpielsessionTO getSessioByLobbyID(int lobbyID) {
		Spielsession aSession = sessionDAO.findSessionByLobbyID(lobbyID);
		return aSession.toSessionTO();
	}
}
