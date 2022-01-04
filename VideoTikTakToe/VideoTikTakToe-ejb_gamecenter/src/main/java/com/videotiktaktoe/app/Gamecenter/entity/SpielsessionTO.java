package com.videotiktaktoe.app.Gamecenter.entity;

import java.io.Serializable;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Spielsession;

public class SpielsessionTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int rundenAnzahl;
	private int lobbyID;
	
	//Konstruktor
	public SpielsessionTO() {}
	
	public SpielsessionTO(int id, int rundenAnzahl, int lobbyID) {
		this.id = id;
		this.rundenAnzahl = rundenAnzahl;
		this.lobbyID = lobbyID;
	}
	
	public Spielsession toSession() {
		Spielsession aSession = new Spielsession(
			this.getId(),
			this.getRundenAnzahl(),
			this.getLobbyID()
		);
		
		return aSession;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRundenAnzahl() {
		return rundenAnzahl;
	}
	public void setRundenAnzahl(int rundenAnzahl) {
		this.rundenAnzahl = rundenAnzahl;
	}
	public int getLobbyID() {
		return lobbyID;
	}
	public void setLobbyID(int lobbyID) {
		this.lobbyID = lobbyID;
	}
	
	

}
