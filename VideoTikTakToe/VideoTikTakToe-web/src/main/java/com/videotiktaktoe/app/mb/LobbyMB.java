package com.videotiktaktoe.app.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;
import com.videotiktaktoe.app.Gamecenter.facade.IGamecenterFacade;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;

@Named("lobbyMB")
@SessionScoped
public class LobbyMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3071715294950111471L;
	
	private LobbyTO aLobby;
	
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
		try {
			this.aLobby = gamecenterFacade.lobbyErstellen(this.aLobby, securityContext.getCallerPrincipal().getName());
			sendInfoMessageToUser("Lobby erstellen.");
			return this.toLobbyAnzeigen();
		} catch(EJBException e) {
			sendErrorMessageToUser("Kann die Lobby nicht erstellen.");
			return this.stayAtSide();
		}
	}
	
	public void generateCode() {
		this.aLobby.setLobbyCode(gamecenterFacade.generateCode(this.aLobby));
	}
	
	public String getLobbygroesse() {
		return "Lobbygroesse: " + this.aLobby.getUsers().size() + "/2";
	}
	
	public String lobbyBeitreten() {
		try {
			this.aLobby = gamecenterFacade.lobbyBeitreten(this.aLobby.getLobbyCode(), securityContext.getCallerPrincipal().getName());
			if(this.aLobby.getLobbyCode() == null) {
				//TODO: hier ne Message im Dialogfenster anzeigen
				sendErrorMessageToUser("Der Lobbycode ist ungültig.");
				return this.stayAtSide();
			}
			return this.toLobbyAnzeigen();
		} catch(EJBException e) {
			sendErrorMessageToUser("Der Lobbycode ist ungültig.");
			return this.stayAtSide();
		}
	}
	
	public String lobbyVerlassen() {
		try {
			gamecenterFacade.lobbyVerlassen(securityContext.getCallerPrincipal().getName());
			sendInfoMessageToUser("Lobby verlassen.");
			this.reinitBean();
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
	public String toLogin() {
		return "BACK_TO_LOGIN";
	}
	
	public String toLobbyAnzeigen() {
		return "LOBBY_ANZEIGEN";
	}
	
	public String stayAtSide() {
		return "";
	}
	
	public String toHauptmenue() {
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
}