package com.videotiktaktoe.app.Gamecenter.facade.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.facade.IGamecenterFacade;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyPflegen;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyVerwalten;

@Stateless
public class GamecenterFacade implements IGamecenterFacade{

	@Inject
	ISpiellobbyPflegen spiellobbyPflegen;
	
	@Inject
	ISpiellobbyVerwalten spiellobbyVerwalten;
	
	@Override
	public LobbyTO lobbyErstellen(LobbyTO aLobbyTO, String userName) {
		return spiellobbyPflegen.spiellobbyErstellen(aLobbyTO, userName);
	}

	@Override
	public String generateCode(LobbyTO aLobbyTO) {
		return spiellobbyPflegen.generateCode(aLobbyTO);
	}

	@Override
	public LobbyTO lobbyBeitreten(String lobbyCode, String username) {
		return spiellobbyVerwalten.lobbyBeitreten(lobbyCode, username);
	}

	@Override
	public void lobbyVerlassen(String username) {
		spiellobbyVerwalten.lobbyVerlassen(username);
	}

}
