package com.videotiktaktoe.app.Gamecenter.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Spielsession;


public class SpielsessionTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int sessionID;
	private String anzahlRunde;
	private int lobbyID;
	
	public SpielsessionTO() {
		super();
	}
	
	public SpielsessionTO(int sessionID, String anzahlRunde, int lobbyID) {
		super();
		this.sessionID = sessionID;
		this.anzahlRunde = anzahlRunde;
		this.lobbyID = lobbyID;
	}
	
	public Spielsession toSpielsession() {
		Spielsession spielsession = new Spielsession(
				this.getSessionID(),
				this.getAnzahlRunde(),
				this.getLobbyID());
				
		return spielsession;
	}
	
	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public String getAnzahlRunde() {
		return anzahlRunde;
	}
	
	public void setAnzahlRunde(String anzahlRunde) {
		this.anzahlRunde = anzahlRunde;
	}
	
	public int getLobbyID() {
		return lobbyID;
	}

	public void setLobbyID(int lobbyID) {
		this.lobbyID = lobbyID;
	}
}
