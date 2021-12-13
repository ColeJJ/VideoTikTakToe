package com.videotiktaktoe.app.Spielerverwaltung.facade;

import javax.ejb.Local;
import javax.inject.Inject;

import com.videotiktaktoe.app.Spielerverwaltung.entity.User;
import com.videotiktaktoe.app.Spielerverwaltung.usecase.IAccountPflegen;

@Local
public interface ISpielerverwaltungFacade {
	
	public User findUserByName(String username);	
	public void userRegistrieren(User aUser);
}
