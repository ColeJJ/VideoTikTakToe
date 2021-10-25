package de.impf.mb;

import java.io.Serializable;

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
import de.impf.patient.usecase.IPatientenSuchen;

@Named("PatientMB")
@RequestScoped
public class PatientMB implements Serializable{

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2340591047267891474L;
	
	@Inject
	IPatientenPflegen patientPflegenFacade;
	
	@Inject
	IPatientenSuchen patientSuchenFacade;
	
	//Variablen
	private PatientTO aPatientTO;
	
	public PatientMB() {
	}
	
	@PostConstruct
	public void initBean() {
		this.aPatientTO = new PatientTO();
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
	
	//AW - Patient anlegen
	public String patientAnlegen() {
		try {
			patientPflegenFacade.patientAnlegen(this.aPatientTO);
			sendInfoMessageToUser("Patient angelegt");
			UserMB.clicked();
			return "PATIENT_ANLEGEN_ABBRECHEN";
		} catch (EJBException e) {
			sendErrorMessageToUser("Kann den Patienten nicht anlegen.");
			return "";
		}
		
	}
	
	//Navigation
	@RolesAllowed("MATERIALMANAGER")
	public String startePatientAnlegen() {
		if (securityContext.isCallerInRole("MATERIALMANAGER")) {
			System.out.println("Anzeigen Patient anlegen");
			UserMB.clicked();
			return "PATIENT_ANLEGEN";
		} else {
			System.out.println("Keine Rechte zum Anzeigen von Patient anlegen");
			return "STAY_ON_PAGE";	
		}
	}
	
	public String patientAnlegenAbbrechenClicked() {
		UserMB.clicked();
		return "PATIENT_ANLEGEN_ABBRECHEN";
	}
	
	public String patientVwAbbruchKlicked() {
		UserMB.clicked();
		return "BACK_TO_HAUPTMENUE";
	}

	public PatientTO getaPatientTO() {
		return aPatientTO;
	}

	public void setaPatientTO(PatientTO aPatientTO) {
		this.aPatientTO = aPatientTO;
	}
	
}
