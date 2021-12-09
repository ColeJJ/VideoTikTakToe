package com.videotiktaktoe.app.Gamecenter.entity.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.HA2.Patient.entity.PatientTO;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name="T_HA2_Patient")
@NamedQuery(name="Patient.findPatientByLastAndFirstName", 
	query="SELECT p from Patient p where p.vorname = :vorname or p.nachname = :nachname")
public class Spielsession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_FIRST_AND_LASTNAME = "Patient.findPatientByLastAndFirstName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HA2_PATIENT_ID")
	@SequenceGenerator(name="HA2_PATIENT_ID", sequenceName="HA2_SEQ_PATIENT_ID", allocationSize = 1)
	private int sessionID;
	
	private String anzahlRunde;
	private int lobbyID;
	
	
	public Spielsession() {
		super();
	}
	
//	public Spielsession() {
//	}
	
	public Spielsession(int sessionID, String anzahlRunde, int lobbyID) {
		super();
		this.sessionID = sessionID;
		this.anzahlRunde = anzahlRunde;
		this.lobbyID = lobbyID;
	}	
	
	public Spielsession(SpielsessionTO einSpielsessionTO) {
		this.sessionID = einSpielsessionTO.getSessionID();
		this.anzahlRunde = einSpielsessionTO.getAnzahlRunde();
		this.lobbyID = einSpielsessionTO.getLobbyID();
	}
	
	public SpielsessionTO toSpielsessionTO() {
		
		SpielsessionTO spielsessionTO = new SpielsessionTO();
		spielsessionTO.setSessionID(this.getSessionID());
		spielsessionTO.setAnzahlRunde(this.getAnzahlRunde());
		spielsessionTO.setLobbyID(this.getLobbyID());
		
		return spielsessionTO;
		
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
