package com.videotiktaktoe.app.Gamecenter.usecase.impl;

import java.util.Random;

import javax.inject.Inject;

import com.videotiktaktoe.app.Gamecenter.dao.LobbyDAO;
import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;
import com.videotiktaktoe.app.Gamecenter.usecase.ISpiellobbyPflegen;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;

public class SpiellobbyPflegen implements ISpiellobbyPflegen{

	@Inject
	LobbyDAO lobbyDAO;
	
	@Inject
	ISpielerverwaltungFacade spielerverwaltungFacade;
	
	@Override
	public LobbyTO spiellobbyErstellen(LobbyTO aLobbyTO, String userName) {
		lobbyDAO.save(aLobbyTO.toLobby());
		Lobby aLobby = lobbyDAO.findLobbyByName(aLobbyTO.getLobbyName());
		
		//User admin bool auf true setzen und FK setzen
		UserTO aUserTO = spielerverwaltungFacade.findUserByName(userName);
		aUserTO.setAdmin(true);
		aUserTO.setLobbyID(aLobby.getId());
		spielerverwaltungFacade.userSichern(aUserTO);
		
		//admin als User der Userliste hinzufügen
		aLobbyTO.addUser(aUserTO);
		
		return aLobbyTO;
	}

	@Override
	public String generateCode(LobbyTO aLobbyTO) {
		Lobby aLobby = lobbyDAO.findLobbyByName(aLobbyTO.getLobbyName());
		
		// create a string of all characters
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	    // create random string builder
	    StringBuilder sb = new StringBuilder();

	    // create an object of Random class
	    Random random = new Random();

	    // specify length of random string
	    int length = 7;

	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = random.nextInt(alphabet.length());

	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	    }

	    String randomString = sb.toString();
	    aLobby.setLobbyCode(randomString);
	    
	    //TODO: Lobby Code auf Einmaligkeit prüfen, bevor es gespeichert wird 
		lobbyDAO.update(aLobby);
		return aLobby.getLobbyCode();
	}


}
