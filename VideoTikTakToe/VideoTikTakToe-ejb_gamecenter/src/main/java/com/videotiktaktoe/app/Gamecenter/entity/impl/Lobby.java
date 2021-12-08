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
public class Lobby implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_FIRST_AND_LASTNAME = "Patient.findPatientByLastAndFirstName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HA2_PATIENT_ID")
	@SequenceGenerator(name="HA2_PATIENT_ID", sequenceName="HA2_SEQ_PATIENT_ID", allocationSize = 1)
	private int lobbyID;
	
	private String lobbyName;
	private boolean videoEinstellung;
	private boolean audioEinstellung;

	@ElementCollection
	@CollectionTable(name="T_HA2_Patient_Termin",
		joinColumns=@JoinColumn(name="PATIENT_NR"))
	private Collection<List> user;
	
	
	public Lobby() {
		this.setUser(new ArrayList<List>());
	}
	
//	public Lobby() {
//	}
	
	public Lobby(String lobbyName, boolean videoEinstellung, boolean audioEinstellung) {
		super();
		this.lobbyName = lobbyName;
		this.videoEinstellung = videoEinstellung;
		this.audioEinstellung = audioEinstellung;
		this.setUser(new ArrayList<List>());
	}
	
	public Lobby(LobbyTO einLobbyTO) {
		this.lobbyID = einLobbyTO.getLobbyID();
		this.lobbyName = einLobbyTO.getLobbyName();
		this.videoEinstellung = einLobbyTO.getVideoEinstellung();
		this.audioEinstellung = einLobbyTO.getAudioEinstellung();
		for (List userID:einLobbyTO.getUser()) {
			this.user.add(userID);
		}
	}
	
	public LobbyTO toLobbyTO() {
		
		LobbyTO lobbyTO = new LobbyTO();
		lobbyTO.setLobbyID(this.getLobbyID());
		lobbyTO.setLobbyName(this.getLobbyName());
		lobbyTO.setVideoEinstellung(this.getVideoEinstellung());
		lobbyTO.setAudioEinstellung(this.getAudioEinstellung());
		for (List userID:this.getUser()) {
			lobbyTO.addUser(userID);
		}
		
		return lobbyTO;
		
	}
	
	
	public int getLobbyID() {
		return lobbyID;
	}

	public void setLobbyID(int lobbyID) {
		this.lobbyID = lobbyID;
	}

	public String getLobbyName() {
		return lobbyName;
	}
	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}
	public boolean getAudioEinstellung() {
		return audioEinstellung;
	}
	public void setAudioEinstellung(boolean audioEinstellung) {
		this.audioEinstellung = audioEinstellung;
	}

	public boolean getVideoEinstellung() {
		return videoEinstellung;
	}
	public void setVideoEinstellung(boolean videoEinstellung) {
		this.videoEinstellung = videoEinstellung;
	}

	public Collection<List> getUser() {
		return user;
	}

	public void setUser(Collection<List> user) {
		this.user = user;
	}

	public void addUser(int userID) {
		this.user.add(Integer.valueOf(userID));
	}
	
}
