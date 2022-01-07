package com.videotiktaktoe.app.Spielerverwaltung.facade;

import java.util.List;

import javax.ejb.Local;

import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.WertungTO;

@Local
public interface ISpielerverwaltungFacade {
	
	public UserTO findUserByName(String username);
	public WertungTO findWertungByUserID(int userID);
	public void userSichern(UserTO aUserTO);
	public void wertungSichern(WertungTO aWertungTO);
	public void userRegistrieren(UserTO aUser);
	public List<UsergroupTO> getAllGroups();
	public List<UserTO> getAllUsersInSameLobby(int lobbyID);
	public boolean checkWertungenExists(int userID);
}
