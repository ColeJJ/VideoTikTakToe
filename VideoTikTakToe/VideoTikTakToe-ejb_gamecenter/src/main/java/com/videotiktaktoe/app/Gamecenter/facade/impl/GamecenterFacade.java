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
	public void lobbyErstellen(LobbyTO aLobby, String userName) {
		spiellobbyPflegen.spiellobbyErstellen(aLobby, userName);
	}

}
