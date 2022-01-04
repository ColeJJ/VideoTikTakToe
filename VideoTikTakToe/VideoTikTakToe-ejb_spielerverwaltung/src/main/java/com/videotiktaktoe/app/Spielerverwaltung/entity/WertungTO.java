package com.videotiktaktoe.app.Spielerverwaltung.entity;

import java.io.Serializable;

import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.Wertung;

public class WertungTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int punktestand;
	private int siege;
	private int niederlagen;
	private int userID;
	
	//Konstruktor
	public WertungTO() {}
	
	public WertungTO(int id, int punktestand, int siege, int niederlagen, int userID) {
		this.id = id;
		this.punktestand = punktestand;
		this.siege = siege;
		this.niederlagen = niederlagen;
		this.userID = userID;
	}
	
	public Wertung toWertung() {
		Wertung aWertung = new Wertung(
			this.getId(),
			this.getPunktestand(),
			this.getSiege(),
			this.getNiederlagen(),
			this.getUserID()
		);			
		
		return aWertung;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPunktestand() {
		return punktestand;
	}
	public void setPunktestand(int punktestand) {
		this.punktestand = punktestand;
	}
	public int getSiege() {
		return siege;
	}
	public void setSiege(int siege) {
		this.siege = siege;
	}
	public int getNiederlagen() {
		return niederlagen;
	}
	public void setNiederlagen(int niederlagen) {
		this.niederlagen = niederlagen;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}
