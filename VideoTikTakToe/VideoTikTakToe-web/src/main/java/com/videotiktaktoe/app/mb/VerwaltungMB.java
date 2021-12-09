package com.videotiktaktoe.app.mb;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("verwaltungMB")
@RequestScoped
public class VerwaltungMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6183980918040292096L;
	
//	Login
	public String registrierenClicked() {
		
		//Implementierung
		//Klappt irgendwie noch nicht
		
		return "REGISTRIEREN";
	}
	
	
//	Registrierung
	public String accountErstellenClicked() {
		
		//Implementierung
		
		return this.toLogin();		//Nach Erstellung eines Accounts wird man zum Login aufgefordert
	}
	
	public String regestrierungAbbrechenClicked() {
		return this.toLogin();
	}
	
	
//	Lobby erstellen
	public String lobbyErstellenClicked() {
		
		//Implementierung
		//Nicht in faces-config implementiert
		
		return "LOBBY_ERSTELLEN";
	}
	
	public String lobbyErstellenAbbrechenClicked() {
		return this.toHauptmenue();
	}
	
	
//	Lobby beitreten
	public String lobbyBeitretenClicked() {
		
		//Implementierung
		//Nicht in faces-config implementiert
		
		return "LOBBY_BEITRETEN";
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
	
	public String toHauptmenue() {
		return "BACK_TO_HAUPTMENUE";
	}
	
	public String toLogin() {
		return "BACK_TO_LOGIN";
	}

}
