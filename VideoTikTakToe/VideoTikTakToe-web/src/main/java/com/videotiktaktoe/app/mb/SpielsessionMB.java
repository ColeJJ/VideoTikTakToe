package com.videotiktaktoe.app.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.SpielsessionTO;
import com.videotiktaktoe.app.Gamecenter.facade.IGamecenterFacade;

@Named("spielsessionMB")
@SessionScoped
public class SpielsessionMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SpielsessionTO aSessionTO;
	private LobbyTO aLobbyTO;
	private int[] bestOfs = {3,5};
	
	//Konstruktor
	public SpielsessionMB() {}
	
	@Inject
	IGamecenterFacade gamecenterFacade;
	
	@PostConstruct
	public void initBean() {
		if(this.aSessionTO == null) {
			this.aSessionTO = new SpielsessionTO();
		}
		
		//Theorie: Wird aus LobbyAnsicht übergeben.. sonst muss die hier noch iwie gesetzt werden
		//this.aLobbyTO = gamecenterFacade.lobbySuchen(this.aSessionTO.getLobbyID());
	}
	
	public void reinitBean() {
		if(this.aSessionTO != null) {
			this.aSessionTO = gamecenterFacade.getSessionByLobbyID(this.aLobbyTO.getId());
		}
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
			this.aSessionTO = gamecenterFacade.spielStarten(this.aSessionTO.getRundenAnzahl(), this.aSessionTO.getLobbyID());
			sendInfoMessageToUser("Spiel wurde gestartet.");
			return this.toGame();
		} catch(EJBException e) {
			sendErrorMessageToUser("Spiel konnte nicht gestartet werden.");
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
}
