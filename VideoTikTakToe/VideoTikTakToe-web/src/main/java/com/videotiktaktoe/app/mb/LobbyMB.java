package com.videotiktaktoe.app.mb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.swing.JOptionPane;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;
import com.videotiktaktoe.app.Gamecenter.facade.IGamecenterFacade;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;

@Named("lobbyMB")
@SessionScoped
public class LobbyMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3071715294950111471L;
	
	private LobbyTO aLobby;
	private UserTO aUserTO;
	
	//Konstruktor
	public LobbyMB() {}
	
	@Inject
	IGamecenterFacade gamecenterFacade;
	
	@Inject
	ISpielerverwaltungFacade spielerverwaltungFacade;
	
	@Inject
	SecurityContext securityContext;
	
	//init
	@PostConstruct
	public void initBean() {
		if(this.aLobby == null) {
			this.aLobby = new LobbyTO();
		}
	}
	
	public void reinitBean() {
		this.aLobby = new LobbyTO();
	}
	
	//Info-Messages
	private void sendInfoMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));	
	}
	
	private void sendErrorMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}
	 
	public String lobbyErstellenClicked() {
		if (this.aLobby.getLobbyName()== null || this.aLobby.getLobbyName().isEmpty()) {
			sendInfoMessageToUser("Bitte geben Sie einen Lobbynamen an.");
	         return null;
	     }
		 
		 Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(this.aLobby.getLobbyName());
	     boolean b = m.find();
	     if (b == true) {
	    	 sendErrorMessageToUser("Kann die Lobby nicht erstellen.");
	    	 return this.stayAtSide();
	     }else {
	    	try {
			this.aLobby = gamecenterFacade.lobbyErstellen(this.aLobby, securityContext.getCallerPrincipal().getName());
			String username = securityContext.getCallerPrincipal().getName();
			aUserTO = spielerverwaltungFacade.findUserByName(username);
			sendInfoMessageToUser("Lobby wurde erstellt.");
			return this.toLobbyAnzeigen();
	    	}catch(EJBException e) {
	    		sendInfoMessageToUser("Lobbyname existiert bereits.");
				return this.stayAtSide();
	    	}
		}
	}
	
	public UserTO updateUser(){
		String username = securityContext.getCallerPrincipal().getName();
		aUserTO = spielerverwaltungFacade.findUserByName(username);
		return aUserTO;
	}
	
	public void generateCode() {
		this.aLobby.setLobbyCode(gamecenterFacade.generateCode(this.aLobby));
	}
	
	public String getLobbygroesse() {
		return "Lobbygroesse: " + this.aLobby.getUsers().size() + "/2";
	}
	
	public String lobbyBeitreten() {
		//check, ob ein lobbycode eingegeben wurde
		if (this.aLobby.getLobbyCode()== null || this.aLobby.getLobbyCode().isEmpty()) {
			sendInfoMessageToUser("Bitte geben Sie einen Lobbynamen an.");
	        return null;
	    }
		
		try {
			this.aLobby = gamecenterFacade.lobbyBeitreten(this.aLobby.getLobbyCode(), securityContext.getCallerPrincipal().getName());
			if(this.aLobby.getLobbyCode() == null ) {
				//TODO: hier ne Message im Dialogfenster anzeigen
				sendErrorMessageToUser("Der Lobbycode ist ungültig.");
				return this.stayAtSide();
			}
			else {
				sendErrorMessageToUser("Lobby erfolgreich beigetreten");
				String username = securityContext.getCallerPrincipal().getName();
				aUserTO = spielerverwaltungFacade.findUserByName(username);
				return this.toLobbyAnzeigen();
			}
		} catch(EJBException e) {
			sendErrorMessageToUser("Der Lobbycode ist ungültig.");
			return this.stayAtSide();
		}
	}

	public String lobbyVerlassen() {
		try {
			gamecenterFacade.lobbyVerlassen(securityContext.getCallerPrincipal().getName());
			this.reinitBean();
			sendErrorMessageToUser("Lobby verlassen.");
			return this.toHauptmenue();
		} catch (EJBException e) {
			sendErrorMessageToUser("Lobby verlassen hat nicht funktioniert.");
			return this.stayAtSide();
		}
	}

	public String lobbyLoeschen() {
		if(gamecenterFacade.lobbyLoeschen(securityContext.getCallerPrincipal().getName(), this.aLobby.getLobbyName())) {
			sendInfoMessageToUser("Lobby geloescht.");
			this.reinitBean();
			return this.toHauptmenue();
		} else {
			sendErrorMessageToUser("Lobby loeschen hat nicht funktioniert.");
			return this.stayAtSide();
		}
	}
	
	//Navigation	
	private String toLobbyAnzeigen() {
		return "LOBBY_ANZEIGEN";
	}
	
	private String stayAtSide() {
		return "";
	}
	
	private String toHauptmenue() {
		return "BACK_TO_HAUPTMENUE";
	}
	
	//Getters and Setters
	public LobbyTO getaLobby() {
		return aLobby;
	}

	public void setaLobby(LobbyTO aLobby) {
		this.aLobby = aLobby;
	}

	public IGamecenterFacade getGamecenterFacade() {
		return gamecenterFacade;
	}

	public void setGamecenterFacade(IGamecenterFacade gamecenterFacade) {
		this.gamecenterFacade = gamecenterFacade;
	}

	public UserTO getaUserTO() {
		return aUserTO;
	}

	public void setaUserTO(UserTO aUserTO) {
		this.aUserTO = aUserTO;
	}
}