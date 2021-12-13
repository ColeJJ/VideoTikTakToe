package com.videotiktaktoe.app.Gamecenter.facade.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.videotiktaktoe.app.Gamecenter.dao.LobbyDAO;
import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;
import com.videotiktaktoe.app.Gamecenter.facade.IGamecenterFacade;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyPflegen;



@Stateless
public class GamecenterFacadeImp implements IGamecenterFacade{

	@Inject
	private LobbyDAO lobbyDAO;
	
	@Inject
	private ISpiellobbyPflegen lobbyErstellen;
	

	@Override
	public void lobbyErstellen(Lobby aLobby) {
		lobbyErstellen.lobbyErstellen(aLobby);
	}

}
