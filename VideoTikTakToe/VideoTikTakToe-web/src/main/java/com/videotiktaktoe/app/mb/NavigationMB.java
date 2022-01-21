package com.videotiktaktoe.app.mb;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("naviMB")
@RequestScoped
public class NavigationMB implements Serializable {
	
	/**
	 *  Diese Klasse dient zur allgemeinen und klassenuebergreifenden Navigation
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -6183980918040292096L;
		
    //Login
	public String starteRegistrierung() {
		return this.toRegistrierung();
	}
	
	public String regestrierungAbbrechenClicked() {
		return this.toLogin();
	}
	
	//Menue
	public String starteLobbyErstellen() {
		return this.toLobbyErstellen();
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
	
	
    //Freundesliste
	public String freudeslisteAbbrechenClicked() {
		return this.toHauptmenue();
	}
	
	//Navigation
	private String toHauptmenue() {
		return "BACK_TO_HAUPTMENUE";
	}
	
	private String toLogin() {
		return "BACK_TO_LOGIN";
	}
	
	private String toRegistrierung() {
		return "REGISTRIEREN";
	}
	
	private String toLobbyErstellen() {
		return "LOBBY_ERSTELLEN";
	}
	
	private String toLobbyBeitreten() {
		return "LOBBY_BEITRETEN";
	}
	
	private String toStarteFreundesliste() {
		return "STARTE_FREUNDESLISTE";
	}

}
