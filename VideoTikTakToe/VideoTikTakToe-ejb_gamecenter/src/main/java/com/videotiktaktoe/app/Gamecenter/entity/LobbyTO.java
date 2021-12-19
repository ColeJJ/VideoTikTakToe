package com.videotiktaktoe.app.Gamecenter.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;

public class LobbyTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String lobbyName;
	private boolean videoEinstellung;
	private boolean audioEinstellung;	
	private String lobbyCode;
	private List<UserTO> users;
	
	public LobbyTO() {}
	
	public LobbyTO(int id, String lobbyName, boolean videoEinstellung, boolean audioEinstellung) {
		this.id = id;
		this.lobbyName = lobbyName;
		this.videoEinstellung = videoEinstellung;
		this.audioEinstellung = audioEinstellung;
		this.users = new ArrayList<UserTO>();
	}
	
	public Lobby toLobby() {
		Lobby aLobby = new Lobby(
			this.getId(),
			this.getLobbyName(),
			this.getVideoEinstellung(),
			this.getAudioEinstellung(),
			this.getLobbyCode(),
			this.getUsers()
		);
				
		return aLobby;
	}
	
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

	public void addUser(UserTO aUser) {
		this.users.add(aUser);
	}

	public String getLobbyCode() {
		return lobbyCode;
	}

	public void setLobbyCode(String lobbyCode) {
		this.lobbyCode = lobbyCode;
	}

	public String toString() {
		return this.id+" "+this.lobbyName;
	}

}
