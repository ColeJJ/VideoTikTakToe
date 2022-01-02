package com.videotiktaktoe.app.Gamecenter.usecase;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;

public interface ISpiellobbyVerwalten {

	public LobbyTO lobbyBeitreten(String lobbyCode, String username);
	public void lobbyVerlassen(String username);
	public boolean lobbyLoeschen(String username, String lobbyName);
}
