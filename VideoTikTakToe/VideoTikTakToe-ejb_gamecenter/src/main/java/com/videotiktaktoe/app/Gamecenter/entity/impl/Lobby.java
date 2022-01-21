package com.videotiktaktoe.app.Gamecenter.entity.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Transient;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;


@Entity
@Access(AccessType.FIELD)
@Table(name="VTTT_lobby")
@NamedQueries({
	@NamedQuery(name="Lobby.findLobbyByLobbyName", query="SELECT l from Lobby l where l.lobbyName = :lobbyName"),
	@NamedQuery(name="Lobby.findLobbyByLobbyCode", query="SELECT l from Lobby l where l.lobbyCode = :lobbyCode")
})
public class Lobby implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8045990893766366556L;

	public static final String FIND_BY_LOBBYNAME = "Lobby.findLobbyByLobbyName";
	public static final String FIND_BY_LOBBYCODE = "Lobby.findLobbyByLobbyCode";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(unique=true, nullable=false)
	private String lobbyName;
	private boolean videoEinstellung;
	private boolean audioEinstellung;
	@Column(unique=true)
	private String lobbyCode;
	
	@Transient
	private List<UserTO> users;
	
	//Konstruktor
	public Lobby() {}
	
	public Lobby(int id, String lobbyName, boolean videoEinstellung, boolean audioEinstellung, String lobbyCode) {
		this.id = id;
		this.lobbyName = lobbyName;
		this.videoEinstellung = videoEinstellung;
		this.audioEinstellung = audioEinstellung;
		this.lobbyCode = lobbyCode;
		this.users = new ArrayList<UserTO>();
	}
	
	public Lobby(int id, String lobbyName, boolean videoEinstellung, boolean audioEinstellung, String lobbyCode, List<UserTO> userListe) {
		this.id = id;
		this.lobbyName = lobbyName;
		this.videoEinstellung = videoEinstellung;
		this.audioEinstellung = audioEinstellung;
		this.lobbyCode = lobbyCode;
		this.users = userListe;
	}
	
	public LobbyTO toLobbyTO() {
		
		LobbyTO lobbyTO = new LobbyTO();
		lobbyTO.setId(this.getId());
		lobbyTO.setLobbyName(this.getLobbyName());
		lobbyTO.setVideoEinstellung(this.getVideoEinstellung());
		lobbyTO.setAudioEinstellung(this.getAudioEinstellung());
		lobbyTO.setLobbyCode(this.getLobbyCode());
		lobbyTO.setUsers(this.getUsers());;
		
		return lobbyTO;
		
	}
	
	public void addUserToLobby(UserTO aUserTO) {
		this.users.add(aUserTO);
	}
	
	//Getters and Setters
	public int getId() {
		return this.id;
	}

	public void setId(int lobbyID) {
		this.id = lobbyID;
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

	public List<UserTO> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserTO> userListe) {
		this.users = userListe;
	}

	public String getLobbyCode() {
		return lobbyCode;
	}

	public void setLobbyCode(String lobbyCode) {
		this.lobbyCode = lobbyCode;
	}
	
}
