package com.videotiktaktoe.app.Spielerverwaltung.facade.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.videotiktaktoe.app.Spielerverwaltung.dao.UserDAO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.User;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;

@Stateless
public class SpielerverwaltungFacadeImp implements ISpielerverwaltungFacade{

	@Inject
	private UserDAO userDAO;
	
	public User findUserByName(String username) {
		return userDAO.findUserByName(username);
	}

}
