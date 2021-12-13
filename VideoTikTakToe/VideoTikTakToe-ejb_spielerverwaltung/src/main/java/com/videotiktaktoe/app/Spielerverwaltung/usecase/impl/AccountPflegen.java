package com.videotiktaktoe.app.Spielerverwaltung.usecase.impl;

import javax.inject.Inject;

import com.videotiktaktoe.app.Spielerverwaltung.dao.UserDAO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.User;
import com.videotiktaktoe.app.Spielerverwaltung.usecase.IAccountPflegen;

public class AccountPflegen implements IAccountPflegen{
	
	@Inject
	UserDAO userDAO;

	@Override
	public void userRegistrieren(UserTO aUser) {
		User user = aUser.toUser();
		userDAO.save(user);
	}
}
