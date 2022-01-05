package com.videotiktaktoe.app.Gamecenter.usecase;


import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;

public interface ISpielVerwalten {

	public SpielsessionTO spielStarten(int anzahlRunden, int lobbyID);
	public SpielsessionTO getSessioByLobbyID(int lobbyID);
}
