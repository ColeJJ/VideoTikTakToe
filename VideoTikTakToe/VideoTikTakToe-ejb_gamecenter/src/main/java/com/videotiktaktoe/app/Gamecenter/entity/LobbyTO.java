package com.videotiktaktoe.app.Gamecenter.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;

public class LobbyTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int lobbyID;
	private String lobbyName;
	private boolean videoEinstellung;
	private boolean audioEinstellung;
	
	private Collection<List> user;
	
	public LobbyTO() {
		this.user = new ArrayList<List>();
	}
	
	public LobbyTO(int lobbyID, String lobbyName, boolean videoEinstellung, boolean audioEinstellung,
			Collection<List> user) {
		super();
		this.lobbyID = lobbyID;
		this.lobbyName = lobbyName;
		this.videoEinstellung = videoEinstellung;
		this.audioEinstellung = audioEinstellung;
		this.user = user;
	}
	
	public Lobby toLobby() {
		Lobby lobby = new Lobby(
//		this.getLobbyID(),
		this.getLobbyName(),
		this.getVideoEinstellung(),
		this.getAudioEinstellung());
		for (List user:this.getUser()) 
		lobby.getUser().add(user);
			
		return lobby;
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

	public void addUser(List userID) {
		this.user.add(userID);
	}

	public String toString() {
		return this.lobbyID+" "+this.lobbyName;
	}

}
