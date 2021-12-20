package com.videotiktaktoe.app.Gamecenter.facade;

import javax.ejb.Local;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;

@Local
public interface IGamecenterFacade {
	
	public LobbyTO lobbyErstellen (LobbyTO aLobby, String userName);
	public String generateCode (LobbyTO aLobbyTO);
}
