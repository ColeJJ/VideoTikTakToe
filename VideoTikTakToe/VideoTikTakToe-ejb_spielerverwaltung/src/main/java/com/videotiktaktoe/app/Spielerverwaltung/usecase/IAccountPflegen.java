package com.videotiktaktoe.app.Spielerverwaltung.usecase;

import java.util.List;

import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;

public interface IAccountPflegen {

	public void userRegistrieren(UserTO aUser);
	public List<UsergroupTO> getAllGroups();
	public List<UserTO> getAllUsersInSameLobby(int lobbyID);
}
