package com.videotiktaktoe.app.Gamecenter.usecase;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;

public interface ISpiellobbyPflegen {
	
	public LobbyTO spiellobbyErstellen(LobbyTO aLobbyTO, String userName);
	public String generateCode(LobbyTO aLobbyTO);
	public LobbyTO lobbySuchen(int lobbyID);

}
