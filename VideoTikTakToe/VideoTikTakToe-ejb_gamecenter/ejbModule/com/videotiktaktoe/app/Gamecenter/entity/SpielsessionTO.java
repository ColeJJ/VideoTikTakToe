package de.VideoTikTakToe.Gamecenter.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import de.VideoTikTakToe.Gamecenter.entity.impl.Spielsession;
import de.bank.kunde.entity.Integer;
import de.bank.kunde.entity.String;
import de.bank.kunde.entity.impl.Kunde;
import de.bank.kunde.entity.impl.Privatkunde;

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
	
	public SpielsessionTO(String sessionID, int anzahlRunde, int lobbyID) {
		super();
		this.sessionID = sessionID;
		this.anzahlRunde = anzahlRunde;
		this.lobbyID = lobbyID;
	}
	
	public Spielsession toSpielsession() {
		Spielsession spielsession = new Spielsession(
//				this.getSessionID(),
				this.getAnzahlRunde());
				this.getLobbyID();
				
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
