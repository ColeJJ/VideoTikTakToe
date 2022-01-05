package com.videotiktaktoe.app.Spielerverwaltung.entity.impl;

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

import com.videotiktaktoe.app.Spielerverwaltung.entity.WertungTO;

@Entity
@Access(AccessType.FIELD)
@Table(name="VTTT_wertung")
@NamedQueries({
	@NamedQuery(name="Wertung.findWertungByUserID", query="SELECT w from Wertung w where w.userID = :userID")
})
public class Wertung implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int punktestand;
	private int siege;
	private int niederlagen;
	@Column(unique=true, nullable=false)
	private int userID;
	
	//Konstruktor
	public Wertung() {};
	
	public Wertung(int id, int punktestand, int siege, int niederlagen, int userID) {
		this.id = id;
		this.punktestand = punktestand;
		this.siege = siege;
		this.niederlagen = niederlagen;
		this.userID = userID;
	}
	
	public Wertung(int punktestand, int siege, int niederlagen, int userID) {
		this.punktestand = punktestand;
		this.siege = siege;
		this.niederlagen = niederlagen;
		this.userID = userID;
	}
	
	public WertungTO toWertungTO() {
		WertungTO aWertungTO = new WertungTO();
		aWertungTO.setId(this.getId());
		aWertungTO.setPunktestand(this.getPunktestand());
		aWertungTO.setSiege(this.getSiege());
		aWertungTO.setNiederlagen(this.getNiederlagen());
		aWertungTO.setUserID(this.getUserID());
		
		return aWertungTO;
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
