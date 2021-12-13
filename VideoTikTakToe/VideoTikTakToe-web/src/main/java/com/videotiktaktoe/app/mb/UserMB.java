package com.videotiktaktoe.app.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;

import com.videotiktaktoe.app.Spielerverwaltung.entity.User;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;

@SessionScoped
@Named("userMB")
public class UserMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5749427890834102605L;
	
	private User aUser;
	
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
	
	@Inject
	private ISpielerverwaltungFacade spielerverwaltungFacade;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	public UserMB() {}
	
	public String userRegistrieren() {
		try {
			spielerverwaltungFacade.userRegistrieren(this.aUser);
			sendInfoMessageToUser("User registrieren.");
			return this.toLogin();
		} catch (EJBException e) {
			sendErrorMessageToUser("Kann den User nicht registrieren.");
			return "";
		}
		
	}
	
	public User getUser(){
		
		System.out.println("getUser() in UserMB called");
		System.out.println("User ist kein Admin: "+securityContext.isCallerInRole("USER"));
		String username = securityContext.getCallerPrincipal().getName();
		aUser = spielerverwaltungFacade.findUserByName(username);
		
		return aUser;
	}
	
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
	public String toLogin() {
		return "BACK_TO_LOGIN";
	}
	
	//Getters and Setters
	public SecurityContext getSecurityContext() {
		return securityContext;
	}

	public void setSecurityContext(SecurityContext securityContext) {
		this.securityContext = securityContext;
	}

	public void setUser(User user) {
		this.aUser = user;
	}
}
