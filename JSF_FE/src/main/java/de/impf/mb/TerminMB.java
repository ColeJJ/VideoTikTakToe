package de.impf.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;

import de.impf.patient.entity.PatientTO;
import de.impf.patient.usecase.IPatientenPflegen;
import de.impf.termin.entity.TerminTO;
import de.impf.termin.usecase.ITerminePflegen;
import de.impf.termin.usecase.ITermineSuchen;

@Named("TerminMB")
@RequestScoped
public class TerminMB implements Serializable{

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3342217031141923805L;
	
	@Inject
	ITerminePflegen terminPflegenFacade;
	
	@Inject
	ITermineSuchen termineSuchenFacade;
	
	@Inject
	IPatientenPflegen patientenPflegenFacade;
	
	//Variblen
	private TerminTO aTerminTO;
	private List<PatientTO> patienten;
	
	public TerminMB() {
	}
	
	@PostConstruct
	public void initBean() {
		this.aTerminTO = new TerminTO();
		this.patienten = new ArrayList<PatientTO>();
		this.patienten = patientenPflegenFacade.getAllPatienten();
	}
	
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
	
	//AW - Termin anlegen
	public String terminAnlegen() {
		try {
			this.aTerminTO.setWahrgenommen(false);
			terminPflegenFacade.terminAnlegen(aTerminTO);
			UserMB.clicked();
			sendInfoMessageToUser("Termin angelegt");
			return "TERMINE_PFLEGEN_ABBRECHEN";
		} catch (EJBException e) {
			sendErrorMessageToUser("Kann den Termin nicht anlegen.");
			return "";
		}	
	}
	
	//Lade Patienten für die SelectMenues
	public void ladePatienten() {		
		this.patienten = patientenPflegenFacade.getAllPatienten();
	}
	
	//Navigation
	public String starteTerminverwaltung() {
		UserMB.clicked();
		return "TERMINEVERWALTUNG_MENUE";
	}
	
	@RolesAllowed("ARZT")
	public String starteTerminAnlegen() {
		if (securityContext.isCallerInRole("ARZT")) {
			System.out.println("Anzeigen Termin pflegen");
			UserMB.clicked();
			return "TERMINE_PFLEGEN";
		} else {
			System.out.println("Keine Rechte zum Anzeigen von Termin pflegen");
			return "STAY_ON_PAGE";	
		}
	}
	
	@RolesAllowed("ARZT")
	public String starteImpfungAnlegen() {
		if (securityContext.isCallerInRole("ARZT")) {
			System.out.println("Anzeigen Impfung pflegen");
			UserMB.clicked();
			return "IMPFUNG_PFLEGEN";
		} else {
			System.out.println("Keine Rechte zum Anzeigen von Impfung pflegen");
			return "STAY_ON_PAGE";	
		}
	}

	public String terminVwAbbruchKlicked() {
		UserMB.clicked();
		return "BACK_TO_HAUPTMENUE";
	}
	
	public String terminPflegenAbbruchKlicked() {
		UserMB.clicked();
		return "TERMINE_PFLEGEN_ABBRECHEN";
	}
	
	@RolesAllowed("ARZT")
	public String starteImpfterminAnsicht() {
		if (securityContext.isCallerInRole("ARZT")) {
			System.out.println("Anzeigen Impfungen");
			UserMB.clicked();
			return "IMPFTERMINE_ANSEHEN";
		} else {
			System.out.println("Keine Rechte zum Anzeigen der Impfungen");
			return "STAY_ON_PAGE";	
		}
	}

	public TerminTO getaTerminTO() {
		return aTerminTO;
	}

	public void setaTerminTO(TerminTO aTerminTO) {
		this.aTerminTO = aTerminTO;
	}

	public List<PatientTO> getPatienten() {
		return patienten;
	}

	public void setPatienten(List<PatientTO> patienten) {
		this.patienten = patienten;
	}
}
