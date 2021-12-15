package com.videotiktaktoe.app.Spielerverwaltung.dao;

import javax.ejb.Stateless;

import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.Usergroup;

@Stateless
public class UsergroupDAO extends GenericDAO<Usergroup> {
	
	public UsergroupDAO() {
		super(Usergroup.class);
	}
	
	//TODO: hier kommt safe noch stuff hin
}
