package com.videotiktaktoe.app.Gamecenter.entity.impl;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;

@Entity
@Access(AccessType.FIELD)
@Table(name="VTTT_spielsession")
@NamedQueries({
	@NamedQuery(name="Spielsession.findSessionByLobbyID", query="SELECT s from Spielsession s where s.lobbyID = :lobbyID")
})
public class Spielsession implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_LOBBYID = "Spielsession.findSessionByLobbyID";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int rundenAnzahl;
	@Column(unique=true, nullable=false)
	private int lobbyID;
	
	//Konstruktor
	public Spielsession() {}
	
	public Spielsession(int rundenAnzahl, int lobbyID) {
		this.rundenAnzahl = rundenAnzahl;
		this.lobbyID = lobbyID;
	}

	public Spielsession(int id, int rundenAnzahl, int lobbyID) {
		this.id = id;
		this.rundenAnzahl = rundenAnzahl;
		this.lobbyID = lobbyID;
	}
	
	public SpielsessionTO toSessionTO() {
		SpielsessionTO sessionTO = new SpielsessionTO();
		sessionTO.setId(this.getId());
		sessionTO.setRundenAnzahl(this.getRundenAnzahl());
		sessionTO.setLobbyID(this.getLobbyID());
		
		return sessionTO;
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
