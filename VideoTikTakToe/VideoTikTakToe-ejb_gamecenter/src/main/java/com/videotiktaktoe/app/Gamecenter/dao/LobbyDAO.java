package com.videotiktaktoe.app.Gamecenter.dao;

import javax.ejb.Stateless;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;

@Stateless
public class LobbyDAO extends GenericDAO<Lobby> {
	
	public LobbyDAO(){
		super(Lobby.class);
	}

}
