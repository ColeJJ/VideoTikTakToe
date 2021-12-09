package com.videotiktaktoe.app.mb;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("menueMB")
@RequestScoped
public class MenueMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5513030987899850282L;
	
	public String starteLobbyErstellen() {
		return "LOBBY_ERSTELLEN";
	}
	
	public String starteLobbyBeitreten() {
		return "LOBBY_BEITRETEN";
	}
	
	public String starteFreundesliste() {
		return "STARTE_FREUNDESLISTE";
	}
	
	public String abmelden() {
		return "ABMELDEN";
	}
 
}
