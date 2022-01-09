package com.videotiktaktoe.app.Gamecenter.facade;

import java.util.List;

import javax.ejb.Local;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;

@Local
public interface IGamecenterFacade {
	
	public LobbyTO lobbyErstellen (LobbyTO aLobbyTO, String userName);
	public String generateCode (LobbyTO aLobbyTO);
	public LobbyTO lobbyBeitreten(String lobbyCode, String username);
	public void lobbyVerlassen(String username);
	public boolean lobbyLoeschen(String username, String lobbyName);
	public SpielsessionTO spielStarten(int anzahlRunden, int lobbyID, List<UserTO> users);
	//TODO:brauchen wir die?
	public LobbyTO lobbySuchen(int lobbyID);
	public SpielsessionTO getSessionByLobbyID(int lobbyID);
	public boolean sessionLoeschen(int sessionID);
}
