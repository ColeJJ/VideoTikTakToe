package com.videotiktaktoe.app.Gamecenter.facade;

import javax.ejb.Local;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;

@Local
public interface IGamecenterFacade {
	
	public LobbyTO lobbyErstellen (LobbyTO aLobbyTO, String userName);
	public String generateCode (LobbyTO aLobbyTO);
	public LobbyTO lobbyBeitreten(String lobbyCode, String username);
	public void lobbyVerlassen(String username);
	public boolean lobbyLoeschen(String username, String lobbyName);
	public SpielsessionTO spielStarten(int anzahlRunden, int lobbyID);
	//TODO:brauchen wir die?
	public LobbyTO lobbySuchen(int lobbyID);
	public SpielsessionTO getSessionByLobbyID(int lobbyID);
}
