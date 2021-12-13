package com.videotiktaktoe.app.Spielerverwaltung.usecase.impl;

import com.videotiktaktoe.app.Spielerverwaltung.dao.UserDAO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.User;
import com.videotiktaktoe.app.Spielerverwaltung.usecase.IAccountPflegen;

public class AccountPflegen implements IAccountPflegen{
	
	private UserDAO userDAO;

	@Override
	public void userRegistrieren(User aUser) {
		userDAO.save(aUser);
	}
	
	

}
