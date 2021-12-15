package com.videotiktaktoe.app.Spielerverwaltung.facade.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.videotiktaktoe.app.Spielerverwaltung.dao.UserDAO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.User;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;
import com.videotiktaktoe.app.Spielerverwaltung.usecase.IAccountPflegen;

@Stateless
public class SpielerverwaltungFacadeImp implements ISpielerverwaltungFacade{

	@Inject
	private UserDAO userDAO;
	
	@Inject
	private IAccountPflegen accountPflegen;
	
	public UserTO findUserByName(String username) {
		User aUser = userDAO.findUserByName(username);
		return aUser.toUserTO();
	}

	@Override
	public void userRegistrieren(UserTO aUser) {
		accountPflegen.userRegistrieren(aUser);
	}

	@Override
	public List<UsergroupTO> getAllGroups() {
		return accountPflegen.getAllGroups();
	}

}
