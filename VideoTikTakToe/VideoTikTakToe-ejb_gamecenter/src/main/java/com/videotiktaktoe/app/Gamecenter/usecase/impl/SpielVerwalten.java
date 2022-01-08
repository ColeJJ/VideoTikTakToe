package com.videotiktaktoe.app.Gamecenter.usecase.impl;

import java.util.List;

import javax.inject.Inject;

import com.videotiktaktoe.app.Gamecenter.dao.SpielsessionDAO;
import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;
import com.videotiktaktoe.app.Gamecenter.entity.impl.Spielsession;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpielVerwalten;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.Wertung;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;

public class SpielVerwalten implements ISpielVerwalten{

	@Inject
	SpielsessionDAO sessionDAO;
	
	@Inject
	ISpielerverwaltungFacade spielerverwaltungFacade;
	
	@Override
	public SpielsessionTO spielStarten(int anzahlRunden, int lobbyID, List<UserTO> users) {
		//Wertungen erstellen, wenn noch nicht vorhanden
		for(UserTO aUserTO:users) {
			if(!spielerverwaltungFacade.checkWertungenExists(aUserTO.getId())) {
				UserTO userTO = spielerverwaltungFacade.findUserByName(aUserTO.getUsername());
				Wertung wertungSpieler = new Wertung(0, 0, 0, userTO.getId());
				spielerverwaltungFacade.wertungSichern(wertungSpieler.toWertungTO());
			}
		}

		//Spielsession erstellen
		Spielsession aSession = new Spielsession(anzahlRunden, lobbyID);
		sessionDAO.save(aSession);
		SpielsessionTO aSessionTO = sessionDAO.findSessionByLobbyID(lobbyID).toSessionTO();
		
		return aSessionTO;
	}

	@Override
	public SpielsessionTO getSessioByLobbyID(int lobbyID) {
		Spielsession aSession = sessionDAO.findSessionByLobbyID(lobbyID);
		return aSession.toSessionTO();
	}
}
