package com.videotiktaktoe.app.Gamecenter.usecase;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;

public interface ISpiellobbyVerwalten {

	public LobbyTO lobbyBeitreten(String lobbyCode);
	
}
