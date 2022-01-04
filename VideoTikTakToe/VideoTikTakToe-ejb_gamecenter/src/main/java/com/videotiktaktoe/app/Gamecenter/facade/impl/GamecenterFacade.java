package com.videotiktaktoe.app.Gamecenter.facade.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;
import com.videotiktaktoe.app.Gamecenter.facade.IGamecenterFacade;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpielVerwalten;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyPflegen;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyVerwalten;

@Stateless
public class GamecenterFacade implements IGamecenterFacade{

	@Inject
	ISpiellobbyPflegen spiellobbyPflegen;
	
	@Inject
	ISpiellobbyVerwalten spiellobbyVerwalten;
	
	@Inject
	ISpielVerwalten spielVerwalten;
	
	//Lobby
	@Override
	public LobbyTO lobbyErstellen(LobbyTO aLobbyTO, String userName) {
		return spiellobbyPflegen.spiellobbyErstellen(aLobbyTO, userName);
	}

	@Override
	public String generateCode(LobbyTO aLobbyTO) {
		return spiellobbyPflegen.generateCode(aLobbyTO);
	}

	@Override
	public LobbyTO lobbyBeitreten(String lobbyCode, String username) {
		return spiellobbyVerwalten.lobbyBeitreten(lobbyCode, username);
	}

	@Override
	public void lobbyVerlassen(String username) {
		spiellobbyVerwalten.lobbyVerlassen(username);
	}

	@Override
	public boolean lobbyLoeschen(String username, String lobbyName) {
		return spiellobbyVerwalten.lobbyLoeschen(username, lobbyName);
	}
	
	public LobbyTO lobbySuchen(int lobbyID) {
		return spiellobbyPflegen.lobbySuchen(lobbyID);
	}

	//Spielsession
	@Override
	public SpielsessionTO spielStarten(int anzahlRunden, int lobbyID) {
		return spielVerwalten.spielStarten(anzahlRunden, lobbyID);
	}


	@Override
	public SpielsessionTO getSessionByLobbyID(int lobbyID) {
		return spielVerwalten.getSessioByLobbyID(lobbyID);
	}

}
