package com.videotiktaktoe.app.Gamecenter.facade;

import javax.ejb.Local;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;



@Local
public interface IGamecenterFacade {
	
	
	public void lobbyErstellen(Lobby aLobby);
}


