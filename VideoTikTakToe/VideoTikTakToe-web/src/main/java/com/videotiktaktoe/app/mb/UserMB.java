package com.videotiktaktoe.app.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
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
@RolesAllowed({"USER"})
public class UserMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5749427890834102605L;
	
	private User user;
	
	@Inject
	private ISpielerverwaltungFacade spielerverwaltungFacade;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	public UserMB() {}
	
	public User getUser(){
		
		System.out.println("getUser() in UserMB called");
		System.out.println("User ist kein Admin: "+securityContext.isCallerInRole("USER"));
		String username = securityContext.getCallerPrincipal().getName();
		user = spielerverwaltungFacade.findUserByName(username);
		
		return user;
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
	
	
	//Getters and Setters
	public SecurityContext getSecurityContext() {
		return securityContext;
	}

	public void setSecurityContext(SecurityContext securityContext) {
		this.securityContext = securityContext;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
