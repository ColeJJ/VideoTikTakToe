package com.videotiktaktoe.app.Spielerverwaltung.facade;

import javax.ejb.Local;

import com.videotiktaktoe.app.Spielerverwaltung.entity.User;

@Local
public interface ISpielerverwaltungFacade {
	
	//hier andere Schnittstellen definieren, die bereitgestellt werden soll
	//zb private IUserRegistrieren userRegistrieren;
	
	
	//Methoden (auch hier Aufruf der anderen UseCase Methoden über dessen Schnittstellen)
	public User findUserByName(String username);
	/*
	public void userRegistrieren() {
		userRegistrieren.meth
	}*/	
}
