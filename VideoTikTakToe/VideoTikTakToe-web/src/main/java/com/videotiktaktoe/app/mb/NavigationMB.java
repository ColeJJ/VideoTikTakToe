package com.videotiktaktoe.app.mb;
import java.io.Serializable;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import javax.security.enterprise.SecurityContext;

@Named("naviMB")
@RequestScoped
@RolesAllowed({"USER","ADMIN"})
public class NavigationMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6183980918040292096L;
	@Inject
	SecurityContext securityContext;
	
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
		
//	Login
	public String starteRegistrierung() {
		return this.toRegistrierung();
	}
	
	public String regestrierungAbbrechenClicked() {
		return this.toLogin();
	}
	
//Menue
	@RolesAllowed({"ADMIN"})
	public String starteLobbyErstellen() {
		if (securityContext.isCallerInRole("ADMIN")) {
			return this.toLobbyErstellen();
		}
		else {
				sendInfoMessageToUser("Keine Rechte um Lobby zu erstellen.");
				return "";	
			}
	}
	
	public String starteLobbyBeitreten() {
		return this.toLobbyBeitreten();
	}
	
	public String starteFreundesliste() {
		return this.toStarteFreundesliste();
	}
	
	public String lobbyErstellenAbbrechenClicked() {
		return this.toHauptmenue();
	}
	
	public String lobbyBeitretenAbbrechenClicked() {
		return this.toHauptmenue();
	}
	
	
//	Freundesliste
	public String freudeslisteAbbrechenClicked() {
		return this.toHauptmenue();
	}
	
	
//	Allgemein nutzbar
//	public String abbrechenToHauptmenueClicked() {
//		return "BACK_TO_HAUPTMENUE";
//	}
//	
//	public String abbrechenToLoginClicked() {
//		return "BACK_TO_LOGIN";
//	}
	
	//Navigation
	public String toHauptmenue() {
		return "BACK_TO_HAUPTMENUE";
	}
	
	public String toLogin() {
		return "BACK_TO_LOGIN";
	}
	
	public String toRegistrierung() {
		return "REGISTRIEREN";
	}
	
	public String toLobbyErstellen() {
		return "LOBBY_ERSTELLEN";
	}
	
	public String toLobbyBeitreten() {
		return "LOBBY_BEITRETEN";
	}
	
	public String toStarteFreundesliste() {
		return "STARTE_FREUNDESLISTE";
	}

}
