package com.videotiktaktoe.app.Gamecenter.facade.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.facade.IGamecenterFacade;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyPflegen;

@Stateless
public class GamecenterFacade implements IGamecenterFacade{

	@Inject
	ISpiellobbyPflegen spiellobbyPflegen;
	
	@Override
	public LobbyTO lobbyErstellen(LobbyTO aLobby, String userName) {
		return spiellobbyPflegen.spiellobbyErstellen(aLobby, userName);
	}

	@Override
	public String generateCode(LobbyTO aLobbyTO) {
		return spiellobbyPflegen.generateCode(aLobbyTO);
	}

}
