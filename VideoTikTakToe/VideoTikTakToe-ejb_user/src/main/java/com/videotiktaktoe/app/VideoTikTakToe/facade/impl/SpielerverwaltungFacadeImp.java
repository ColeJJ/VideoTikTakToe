package com.videotiktaktoe.app.VideoTikTakToe.facade.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.videotiktaktoe.app.VideoTikTakToe.dao.UserDAO;
import com.videotiktaktoe.app.VideoTikTakToe.entity.User;
import com.videotiktaktoe.app.VideoTikTakToe.facade.ISpielerverwaltungFacade;

@Stateless
public class SpielerverwaltungFacadeImp implements ISpielerverwaltungFacade{

	@Inject
	private UserDAO userDAO;
	
	public User findUserByName(String username) {
		return userDAO.findUserByName(username);
	}

}
