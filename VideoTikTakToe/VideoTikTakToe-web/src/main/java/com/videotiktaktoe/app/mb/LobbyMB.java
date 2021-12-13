package com.videotiktaktoe.app.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;

import com.videotiktaktoe.app.Gamecenter.entity.LobbyTO;
import com.videotiktaktoe.app.Gamecenter.entity.impl.Lobby;
import com.videotiktaktoe.app.Gamecenter.facade.IGamecenterFacade;



@Named("lobbyMB")
@SessionScoped
public class LobbyMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5749427890834102605L;
	
	
	
	//Info-Messages
	private void sendInfoMessageToLobby(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	private void sendErrorMessageToLobby(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}
	
	@Inject
	private IGamecenterFacade gamecenterFacade;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	public LobbyMB() {}
	
	private Lobby aLobby;
	
	@PostConstruct
	public void initBean() {

		this.aLobby = new Lobby();
//		this.setErgebnisListe(new List<KundeTO>());
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String lobbyErstellen() {
		try {
			gamecenterFacade.lobbyErstellen(this.aLobby);
			sendInfoMessageToLobby("Lobby erstellen.");
			return this.toHauptmenue();
		} catch (EJBException e) {
			sendErrorMessageToLobby("Kann die Lobby nicht erstellen.");
			return "";
		}
		
	}
	
	/*
	public User getUser(){
		
		System.out.println("getUser() in UserMB called");
		System.out.println("User ist kein Admin: "+securityContext.isCallerInRole("USER"));
		String username = securityContext.getCallerPrincipal().getName();
		aUser = spielerverwaltungFacade.findUserByName(username);
		
		return aUser;
	}*/
	
	@RolesAllowed({"USER"})
	public String logOut() throws IOException, ServletException{
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		try {
			ec.redirect(ec.getApplicationContextPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Logout; USER: "+securityContext.getCallerPrincipal().getName());
		return "LOGOUT";
	}
	
	//Navigation
	public String toHauptmenue() {
		return "BACK_TO_HAUPTMENUE";
	}
	
	//Getters and Setters
	public SecurityContext getSecurityContext() {
		return securityContext;
	}

	public void setSecurityContext(SecurityContext securityContext) {
		this.securityContext = securityContext;
	}

	public Lobby getaLobby() {
		return aLobby;
	}

	
	public void setaLobby(Lobby aLobby) {
		if (this.aLobby == null) {
			this.aLobby = new Lobby();
		}
		this.aLobby = aLobby;
	}
}
