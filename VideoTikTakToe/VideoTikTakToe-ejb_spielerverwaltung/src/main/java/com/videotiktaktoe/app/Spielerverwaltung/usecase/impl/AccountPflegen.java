package com.videotiktaktoe.app.Spielerverwaltung.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.videotiktaktoe.app.Spielerverwaltung.dao.UserDAO;
import com.videotiktaktoe.app.Spielerverwaltung.dao.UsergroupDAO;
import com.videotiktaktoe.app.Spielerverwaltung.dao.WertungDAO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.WertungTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.User;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.Usergroup;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.Wertung;
import com.videotiktaktoe.app.Spielerverwaltung.usecase.IAccountPflegen;

public class AccountPflegen implements IAccountPflegen{
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	WertungDAO wertungDAO;
	
	@Inject
	UsergroupDAO groupDAO;

	@Override
	public void userRegistrieren(UserTO aUser) {
		User user = aUser.toUser();
		userDAO.save(user);
	}

	@Override
	public List<UsergroupTO> getAllGroups() {
		List<UsergroupTO> groupTOListe = new ArrayList<UsergroupTO>();
		List<Usergroup> groupListe = groupDAO.findAll();
		for(Usergroup aGroup:groupListe) {
			groupTOListe.add(aGroup.toUsergroupTO());
		}
		return groupTOListe;
	}

	@Override
	public List<UserTO> getAllUsersInSameLobby(int lobbyID) {
		List<UserTO> userTOListe = new ArrayList<UserTO>();
		List<User> userListe = userDAO.findAllUsersByLobbyID(lobbyID);
		for(User aUser:userListe) {
			userTOListe.add(aUser.toUserTO());
		}
		return userTOListe;
	}

	@Override
	public UserTO findUserByName(String username) {
		User aUser = userDAO.findUserByName(username);
		return aUser != null ? aUser.toUserTO() : null;
	}
	
	@Override
	public WertungTO findWertungByUserID(int userID) {
		Wertung aWertung = wertungDAO.findWertungByUserID(userID);
		return aWertung != null ? aWertung.toWertungTO() : null;
	}

	@Override
	public void userSichern(UserTO aUserTO) {
		userDAO.update(aUserTO.toUser());
	}

	@Override
	public void wertungSichern(WertungTO aWertungTO) {
		wertungDAO.update(aWertungTO.toWertung());
	}

	@Override
	public boolean checkWertungenExists(int userID) {
		if(this.findWertungByUserID(userID) != null) {
			return true;
		} else {
			return false;
		}
	}
}
