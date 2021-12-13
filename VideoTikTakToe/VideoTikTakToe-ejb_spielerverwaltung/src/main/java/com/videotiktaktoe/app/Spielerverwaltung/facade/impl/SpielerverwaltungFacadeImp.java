package com.videotiktaktoe.app.Spielerverwaltung.facade.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.videotiktaktoe.app.Spielerverwaltung.dao.UserDAO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.User;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;
import com.videotiktaktoe.app.Spielerverwaltung.usecase.IAccountPflegen;

@Stateless
public class SpielerverwaltungFacadeImp implements ISpielerverwaltungFacade{

	@Inject
	private UserDAO userDAO;
	
	@Inject
	private IAccountPflegen accountErstellen;
	
	public User findUserByName(String username) {
		return userDAO.findUserByName(username);
	}

	@Override
	public void userRegistrieren(User aUser) {
		accountErstellen.userRegistrieren(aUser);
	}

}
