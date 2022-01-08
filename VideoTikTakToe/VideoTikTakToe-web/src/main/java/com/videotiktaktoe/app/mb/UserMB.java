package com.videotiktaktoe.app.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
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
import javax.swing.JOptionPane;

import com.videotiktaktoe.app.Spielerverwaltung.entity.UserTO;
import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;
import com.videotiktaktoe.app.Spielerverwaltung.facade.ISpielerverwaltungFacade;



@SessionScoped
@Named("userMB")

public class UserMB implements Serializable{
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5749427890834102605L;
	
	private UserTO aUser;
	private List<UsergroupTO> usergroups;
	String confirmPassword;
	
	//Konstruktor
	public UserMB() {}
	
	@Inject
	private ISpielerverwaltungFacade spielerverwaltungFacade;
	
	//init
	@PostConstruct
	public void initBean() {
		this.aUser = new UserTO();
		this.usergroups = new ArrayList<UsergroupTO>();
		this.usergroups = spielerverwaltungFacade.getAllGroups();
	}
	
	public void loadUsergroups() {		
		System.out.println("Usergruppen werden geladen");
		this.usergroups = spielerverwaltungFacade.getAllGroups();
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
	
	public String userRegistrieren(){
		if (this.aUser.getUsername() == null || this.aUser.getUsername().trim().isEmpty()) {
			sendInfoMessageToUser("Bitte geben Sie die vollständigen Daten ein.");
	         return null;
	     }
	     Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Pattern e = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
	     Matcher m = p.matcher(this.aUser.getUsername());
	     boolean n = e.matcher(this.aUser.geteMailAddress()).matches();
	    // boolean b = m.matches();
	     boolean b = m.find();
	     	
	     if (b == true || n == false) {
	        System.out.println("Falsche Syntax bei Username oder E-Mail.");
	     	sendInfoMessageToUser("Kann den User nicht registrieren.");
			return this.stayOnSide();
	     }
	     else {
	    	if(this.aUser.getPassword().equals(confirmPassword)) {
	    	spielerverwaltungFacade.userRegistrieren(this.aUser);
	    	System.out.println("Korrekte Sxntax bei Username und E-Mail.");
			sendInfoMessageToUser("User registrieren.");
			return this.toLogin();
	    	}
	    	else {
	    	sendInfoMessageToUser("Passwörter stimmen nicht überein.");
	    	return this.stayOnSide();
	    	}
	     }
		
	}
	
	
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
	
	public String stayOnSide() {
		return "";
	}
	
	//Getters and Setters
	public SecurityContext getSecurityContext() {
		return securityContext;
	}

	public void setSecurityContext(SecurityContext securityContext) {
		this.securityContext = securityContext;
	}
	
	public UserTO getUser(){
		
		System.out.println("getUser() in UserMB called");
		System.out.println("User ist kein Admin: "+securityContext.isCallerInRole("USER"));
		String username = securityContext.getCallerPrincipal().getName();
		aUser = spielerverwaltungFacade.findUserByName(username);
		
		return aUser;
	}
	
	public UserTO getaUser() {
		return aUser;
	}

	public void setaUser(UserTO aUser) {
		this.aUser = aUser;
	}

	public List<UsergroupTO> getUsergroups() {
		return usergroups;
	}

	public void setUsergroups(List<UsergroupTO> usergroups) {
		this.usergroups = usergroups;
	}

	public ISpielerverwaltungFacade getSpielerverwaltungFacade() {
		return spielerverwaltungFacade;
	}

	public void setSpielerverwaltungFacade(ISpielerverwaltungFacade spielerverwaltungFacade) {
		this.spielerverwaltungFacade = spielerverwaltungFacade;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
