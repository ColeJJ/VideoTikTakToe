package com.videotiktaktoe.app.Spielerverwaltung.usecase;

import java.util.List;

import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.WertungTO;

public interface IAccountPflegen {

	public void userRegistrieren(UserTO aUser);
	public List<UsergroupTO> getAllGroups();
	public List<UserTO> getAllUsersInSameLobby(int lobbyID);
	public UserTO findUserByName(String username);
	public WertungTO findWertungByUserID(int userID);
	public void userSichern(UserTO aUserTO);
	public void wertungSichern(WertungTO aWertungTO);
	public boolean checkWertungenExists(int userID);
}
