package com.videotiktaktoe.app.Spielerverwaltung.facade.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;
import com.videotiktaktoe.app.Spielerverwaltung.usecase.IAccountPflegen;

@Stateless
public class SpielerverwaltungFacadeImp implements ISpielerverwaltungFacade{
	
	@Inject
	private IAccountPflegen accountPflegen;

	public UserTO findUserByName(String username) {
		return accountPflegen.findUserByName(username);
	}
	
	@Override
	public void userSichern(UserTO aUserTO) {
		accountPflegen.userSichern(aUserTO);
	}

	@Override
	public void userRegistrieren(UserTO aUser) {
		accountPflegen.userRegistrieren(aUser);
	}

	@Override
	public List<UsergroupTO> getAllGroups() {
		return accountPflegen.getAllGroups();
	}

	@Override
	public List<UserTO> getAllUsersInSameLobby(int lobbyID) {
		return accountPflegen.getAllUsersInSameLobby(lobbyID);
	}
}
