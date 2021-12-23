package com.videotiktaktoe.app.Gamecenter.usecase.impl;

import javax.inject.Inject;

import com.videotiktaktoe.app.Gamecenter.dao.LobbyDAO;
import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyVerwalten;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.User;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;

public class SpiellobbyVerwalten implements ISpiellobbyVerwalten {

	@Inject
	LobbyDAO lobbyDAO;
	
	@Inject
	ISpielerverwaltungFacade spielerverwaltungFacade;
	
	@Override
	public LobbyTO lobbyBeitreten(String lobbyCode, String username) {
		Lobby aLobby = lobbyDAO.findLobbyByCode(lobbyCode);
		UserTO aUserTO = spielerverwaltungFacade.findUserByName(username);
		LobbyTO aLobbyTO = new LobbyTO();
		if(aLobby != null) {
			aUserTO.setLobbyID(aLobby.getId());
			spielerverwaltungFacade.userSichern(aUserTO);
			aLobby.setUsers(spielerverwaltungFacade.getAllUsersInSameLobby(aLobby.getId()));
			aLobbyTO = aLobby.toLobbyTO();
		}

		//TODO: vllt gibts hier in Java auch Möglichkeiten Exceptions zu werfen, die dann im FE abgefangen werden.. 
		//tendenziell brauch man ja nicht n Objekt zurückgeben, wenn der Code ungültig ist
		return aLobbyTO;
	}

}
