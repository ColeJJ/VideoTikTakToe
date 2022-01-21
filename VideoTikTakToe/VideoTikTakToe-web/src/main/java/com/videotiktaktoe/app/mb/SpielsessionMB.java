package com.videotiktaktoe.app.mb;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.ApplicationScoped;
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
@ApplicationScoped
public class SpielsessionMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SpielsessionTO aSessionTO;
	private LobbyTO aLobbyTO;
	private WertungTO aWertungTOSpieler1;
	private WertungTO aWertungTOSpieler2;
	private boolean isAdmin;
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
		this.isAdmin = spielerverwaltungFacade.findUserByName(securityContext.getCallerPrincipal().getName()).isAdmin();	
	}
	
	public void refreshBean() {
		this.aWertungTOSpieler1 = null;
		this.aWertungTOSpieler2 = null;
	}
	
	//Info-Messages
	private void sendInfoMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	private void sendErrorMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}
	
	public void setzeWertungen() {
		this.aWertungTOSpieler1.setPunktestand(0);
		this.aWertungTOSpieler1.setPunktestand(0);
	}
	public String spielStarten() {
		//Check, ob die Lobby vorhanden ist oder nicht vorher schon geloescht wurde
		this.aLobbyTO = gamecenterFacade.lobbySuchen(this.aSessionTO.getLobbyID());
		if(this.aLobbyTO == null) {
			sendErrorMessageToUser("Die Lobby wurde geloescht. Bitte verlassen Sie die Lobby");
			return this.stayAtSide();
		}
		
		if(this.isUserAdmin()) {
			try {
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
		} else {
			try {
				//hier ein kleiner Delay fuer die Websockets Sessions, die kein Admin sind und die BE Requests nicht aufrufen
				TimeUnit.SECONDS.sleep(1);
				return this.toGame();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return this.stayAtSide();
			}
		}
	}
	
	
	public String spielBeenden() {
		if(this.isUserAdmin()) {
			try {
				spielerverwaltungFacade.wertungSichern(aWertungTOSpieler1);
				spielerverwaltungFacade.wertungSichern(aWertungTOSpieler2);	
				
				if(gamecenterFacade.sessionLoeschen(this.aSessionTO.getId())) {
					sendInfoMessageToUser("Spiel wurde geloescht.");
					return this.toLobby();
				} else {
					sendErrorMessageToUser("Spiel konnte nicht geloescht werden.");
					return this.toLobby();
				}
			} catch(EJBException e) {
				sendErrorMessageToUser("Wertungen konnten nicht gesichert werden.");
				return this.toLobby();
			}
		} else {
			try {
				//hier ein kleiner Delay fuer die Websockets Sessions, die kein Admin sind und die BE Requests nicht aufrufen
				TimeUnit.SECONDS.sleep(1);
				return this.toLobby();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return this.stayAtSide();
			}
		}
	}
	
		public String spielAbbrechen() {
			if(this.isUserAdmin()) {
				if(gamecenterFacade.sessionLoeschen(this.aSessionTO.getId())) {
					sendInfoMessageToUser("Spiel wurde abgebrochen.");
					return this.toLobby();
				} else {
					sendErrorMessageToUser("Spiel konnte nicht abgebrochen werden, weil es nicht gelöscht werden konnte.");
					return this.stayAtSide();
				}
			} else {
				try {
					//hier ein kleiner Delay fuer die Websockets Sessions, die kein Admin sind und die BE Requests nicht aufrufen
					TimeUnit.SECONDS.sleep(1);
					return this.toLobby();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return this.stayAtSide(); 
				}
			}
		}
	
	private boolean isUserAdmin() {
		return spielerverwaltungFacade.findUserByName(securityContext.getCallerPrincipal().getName()).isAdmin();
	}
	
	//Navigation	
	private String toGame() {
		return "TO_GAME";
	}
	
	private String stayAtSide() {
		return "";
	}
	
	private String toLobby() {
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
