package com.videotiktaktoe.app.Gamecenter.entity.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name="VTTT_lobby")
@NamedQuery(name="Lobby.findLobbyByLobbyName", 
	query="SELECT l from Lobby l where l.lobbyName = :lobbyname")
public class Lobby implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_LOBBYNAME = "Lobby.findLobbyByLobbyName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String lobbyName;
	private boolean videoEinstellung;
	private boolean audioEinstellung;
	private String lobbyCode;
	private List<UserTO> users;
	
	
	//Konstruktor
	public Lobby() {}
	
	public Lobby(int id, String lobbyName, boolean videoEinstellung, boolean audioEinstellung) {
		this.id = id;
		this.lobbyName = lobbyName;
		this.videoEinstellung = videoEinstellung;
		this.audioEinstellung = audioEinstellung;
		this.users = new ArrayList<UserTO>();
	}
	
	public Lobby(int id, String lobbyName, boolean videoEinstellung, boolean audioEinstellung, List<UserTO> userListe) {
		this.id = id;
		this.lobbyName = lobbyName;
		this.videoEinstellung = videoEinstellung;
		this.audioEinstellung = audioEinstellung;
		this.users = userListe;
	}
	
	public LobbyTO toLobbyTO() {
		
		LobbyTO lobbyTO = new LobbyTO();
		lobbyTO.setId(this.getId());
		lobbyTO.setLobbyName(this.getLobbyName());
		lobbyTO.setVideoEinstellung(this.getVideoEinstellung());
		lobbyTO.setAudioEinstellung(this.getAudioEinstellung());
		lobbyTO.setUsers(this.getUsers());;
		
		return lobbyTO;
		
	}
	
	public void generateCode() {
	    // create a string of all characters
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	    // create random string builder
	    StringBuilder sb = new StringBuilder();

	    // create an object of Random class
	    Random random = new Random();

	    // specify length of random string
	    int length = 7;

	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = random.nextInt(alphabet.length());

	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	    }

	    String randomString = sb.toString();
	    this.setLobbyCode(randomString);
	    System.out.println("LobbyCode is: " + randomString);
	}
	
	//TODO: Spiellobby beitreten
	public void addUserToLobby(UserTO aUser) {
		this.users.add(aUser);
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
