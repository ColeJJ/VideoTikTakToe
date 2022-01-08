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
import com.videotiktaktoe.app.Spielerverwaltung.entity.WertungTO;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;

@Named("spielsessionMB")
@SessionScoped
public class SpielsessionMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SpielsessionTO aSessionTO;
	private LobbyTO aLobbyTO;
	private WertungTO aWertungTOSpieler1;
	private WertungTO aWertungTOSpieler2;
	private int[] bestOfs = {3,5};
	
	//Konstruktor
	public SpielsessionMB() {}
	
	@Inject
	IGamecenterFacade gamecenterFacade;
	
	@Inject
	ISpielerverwaltungFacade spielerverwaltungFacade;
	
	@Inject
	SecurityContext securityContext;
	
	@PostConstruct
	public void initBean() {
		if(this.aSessionTO == null) {
			this.aSessionTO = new SpielsessionTO();
		}
		this.aLobbyTO = new LobbyTO();
		this.aWertungTOSpieler1 = new WertungTO();
		this.aWertungTOSpieler2 = new WertungTO();
	}
	
	public void refreshBean() {
		this.aLobbyTO = null;
		this.aWertungTOSpieler1 = null;
		this.aWertungTOSpieler2 = null;
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
	
	public String spielStarten() {
		try {
			this.aLobbyTO = gamecenterFacade.lobbySuchen(this.aSessionTO.getLobbyID());
			this.aLobbyTO.setUsers(spielerverwaltungFacade.getAllUsersInSameLobby(this.aLobbyTO.getId()));
			this.aSessionTO = gamecenterFacade.spielStarten(this.aSessionTO.getRundenAnzahl(), this.aLobbyTO.getId(), this.aLobbyTO.getUsers());
			this.aWertungTOSpieler1 = spielerverwaltungFacade.findWertungByUserID(this.aLobbyTO.getUsers().get(0).getId());
			this.aWertungTOSpieler2 = spielerverwaltungFacade.findWertungByUserID(this.aLobbyTO.getUsers().get(1).getId());
			sendInfoMessageToUser("Spiel wurde gestartet.");
			return this.toGame();
		} catch(EJBException e) {
			sendErrorMessageToUser("Spiel konnte nicht gestartet werden.");
			return this.stayAtSide();
		}
	}
	
	public String spielBeenden() {
		try {
			spielerverwaltungFacade.wertungSichern(aWertungTOSpieler1);
			spielerverwaltungFacade.wertungSichern(aWertungTOSpieler2);
			sendInfoMessageToUser("Spiel wurde beendet.");
			return this.toLobby();
		} catch(EJBException e) {
			sendErrorMessageToUser("Spiel konnte nicht beendet werden.");
			return this.stayAtSide();
		}
	}
	
	public String spielVerlassen() {
		try {
			this.refreshBean();
			sendInfoMessageToUser("Spiel wurde verlassen.");
			return this.toLobby();
		} catch(EJBException e) {
			sendErrorMessageToUser("Spiel konnte nicht verlassen werden.");
			return this.stayAtSide();
		}
	}
	
	//Navigation
	public String toLogin() {
		return "BACK_TO_LOGIN";
	}
	
	public String toGame() {
		return "TO_GAME";
	}
	
	public String stayAtSide() {
		return "";
	}
	
	public String toHauptmenue() {
		return "BACK_TO_HAUPTMENUE";
	}
	
	public String toLobby() {
		return "LOBBY_ANZEIGEN";
	}
		
	//Getters and Setters
	public SpielsessionTO getaSessionTO() {
		return aSessionTO;
	}
	public void setaSessionTO(SpielsessionTO aSessionTO) {
		this.aSessionTO = aSessionTO;
	}
	public LobbyTO getaLobbyTO() {
		return aLobbyTO;
	}
	public void setaLobbyTO(LobbyTO aLobbyTO) {
		this.aLobbyTO = aLobbyTO;
	}

	public int[] getBestOfs() {
		return bestOfs;
	}

	public void setBestOfs(int[] bestOfs) {
		this.bestOfs = bestOfs;
	}

	public IGamecenterFacade getGamecenterFacade() {
		return gamecenterFacade;
	}

	public void setGamecenterFacade(IGamecenterFacade gamecenterFacade) {
		this.gamecenterFacade = gamecenterFacade;
	}

	public WertungTO getaWertungTOSpieler1() {
		return aWertungTOSpieler1;
	}

	public void setaWertungTOSpieler1(WertungTO aWertungTOSpieler1) {
		this.aWertungTOSpieler1 = aWertungTOSpieler1;
	}

	public WertungTO getaWertungTOSpieler2() {
		return aWertungTOSpieler2;
	}

	public void setaWertungTOSpieler2(WertungTO aWertungTOSpieler2) {
		this.aWertungTOSpieler2 = aWertungTOSpieler2;
	}
}
