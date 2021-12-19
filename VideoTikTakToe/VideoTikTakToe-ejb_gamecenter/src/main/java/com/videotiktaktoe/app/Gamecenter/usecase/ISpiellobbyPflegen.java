package com.videotiktaktoe.app.Gamecenter.usecase;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;

public interface ISpiellobbyPflegen {
	
	public void SpiellobbyErstellen(LobbyTO aLobby, int userID);

}
