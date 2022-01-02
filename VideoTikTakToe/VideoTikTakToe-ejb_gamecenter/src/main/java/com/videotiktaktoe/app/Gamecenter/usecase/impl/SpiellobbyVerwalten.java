package com.videotiktaktoe.app.Gamecenter.usecase.impl;

import java.util.List;

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

	@Override
	public void lobbyVerlassen(String username) {
		UserTO aUserTO = spielerverwaltungFacade.findUserByName(username);
		aUserTO.setLobbyID(0);
		spielerverwaltungFacade.userSichern(aUserTO);
	}

	@Override
	public boolean lobbyLoeschen(String username, String lobbyName) {
		//Alle User aus der Lobby nehmen
		Lobby aLobby = lobbyDAO.findLobbyByName(lobbyName);
		List<UserTO> userTOListe = spielerverwaltungFacade.getAllUsersInSameLobby(aLobby.getId());
		for(UserTO aUserTO:userTOListe) {
			aUserTO.setLobbyID(0);
			if(aUserTO.isAdmin()) {
				aUserTO.setAdmin(false);
			}
			spielerverwaltungFacade.userSichern(aUserTO);
		}
		
		return lobbyDAO.deleteLobby(aLobby.getId());
	}

}
