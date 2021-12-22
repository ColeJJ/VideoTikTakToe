package com.videotiktaktoe.app.Spielerverwaltung.facade;

import java.util.List;

import javax.ejb.Local;

import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;

@Local
public interface ISpielerverwaltungFacade {
	
	public UserTO findUserByName(String username);	
	public void userRegistrieren(UserTO aUser);
	public List<UsergroupTO> getAllGroups();
	public List<UserTO> getAllUsersInSameLobby(int lobbyID);
}
