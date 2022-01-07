package com.videotiktaktoe.app.Gamecenter.usecase;


import java.util.List;

import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;

public interface ISpielVerwalten {

	public SpielsessionTO spielStarten(int anzahlRunden, int lobbyID, List<UserTO> users);
	public SpielsessionTO getSessioByLobbyID(int lobbyID);
}
