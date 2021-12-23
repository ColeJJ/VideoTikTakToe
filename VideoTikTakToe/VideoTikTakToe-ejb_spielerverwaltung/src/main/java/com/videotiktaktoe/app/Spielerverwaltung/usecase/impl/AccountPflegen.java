package com.videotiktaktoe.app.Spielerverwaltung.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.videotiktaktoe.app.Spielerverwaltung.dao.UserDAO;
import com.videotiktaktoe.app.Spielerverwaltung.dao.UsergroupDAO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.User;
import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.Usergroup;
import com.videotiktaktoe.app.Spielerverwaltung.usecase.IAccountPflegen;

public class AccountPflegen implements IAccountPflegen{
	
	@Inject
	UserDAO userDAO;
	
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
		return aUser.toUserTO();
	}

	@Override
	public void userSichern(UserTO aUserTO) {
		userDAO.update(aUserTO.toUser());
	}
}
