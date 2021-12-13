package com.videotiktaktoe.app.Spielerverwaltung.facade;

import javax.ejb.Local;

import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;

@Local
public interface ISpielerverwaltungFacade {
	
	public UserTO findUserByName(String username);	
	public void userRegistrieren(UserTO aUser);
}
