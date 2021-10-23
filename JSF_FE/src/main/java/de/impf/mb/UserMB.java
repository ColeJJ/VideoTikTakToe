package de.impf.mb;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;

import de.impf.security.entity.User;
import de.impf.security.facade.IUserFacade;


@SessionScoped
@Named("userMB")
@RolesAllowed({"MATERIALMANAGER","ARZT"})
public class UserMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4350145920139285844L;

	private User user;
	private static int clicks = 0;
	
	//Zeitelemente fuer die Session-Zeit-Anzeige
	private static long start = System.nanoTime();
	private static long elapsedTime = 0;
	private static long time = 0;
	
	@Inject
	private IUserFacade userFacade;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	public UserMB() {}
	
	public User getUser(){
		
		System.out.println("getUser() in UserMB called");
		if(user == null && securityContext.isCallerInRole("MATERIALMANAGER")){	
			System.out.println("User hat Rolle MATERIALMANAGER: "+securityContext.isCallerInRole("MATERIALMANAGER"));
			String username = securityContext.getCallerPrincipal().getName();
			user = userFacade.findUserByName(username);
		}else {
			System.out.println("User hat Rolle ARZT: "+securityContext.isCallerInRole("ARZT"));
			String username = securityContext.getCallerPrincipal().getName();
			user = userFacade.findUserByName(username);
		}
		
		return user;
	}
	
	//Bools um zu ueberpruefen, ob Buttons im Frontend disabled werden  
	public boolean isUserArzt(){
		return securityContext.isCallerInRole("ARZT");
	}
	
	public boolean isUserMaterialmanager(){
		return securityContext.isCallerInRole("MATERIALMANAGER");
	}
	
	public String logOut() throws IOException, ServletException{
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		try {
			ec.redirect(ec.getApplicationContextPath());
			UserMB.resetClicksAndTime();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Logout; USER: "+securityContext.getCallerPrincipal().getName());
		return "LOGOUT";
	}
	
	//Methode, um die Button Clicks zu zaehlen
	public static void clicked() {
		UserMB.clicks += 1;
		UserMB.elapsedTime = System.nanoTime() - UserMB.start;
		UserMB.time = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
	}
	
	//Session-Zeit reset bei Logout
	public static void resetClicksAndTime() {
		UserMB.clicks = 0;
		UserMB.time = 0;
		UserMB.elapsedTime = 0;
		UserMB.start = System.nanoTime();
	}

	public long getStart() {
		return UserMB.start;
	}

	public void setStart(long start) {
		UserMB.start = start;
	}

	public long getElapsedTime() {
		return UserMB.elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		UserMB.elapsedTime = elapsedTime;
	}

	public int getClicks() {
		return UserMB.clicks;
	}

	public void setClicks(int clicks) {
		UserMB.clicks = clicks;
	}

	public long getTime() {
		return UserMB.time;
	}

	public void setTime(long time) {
		UserMB.time = time;
	}
	
}