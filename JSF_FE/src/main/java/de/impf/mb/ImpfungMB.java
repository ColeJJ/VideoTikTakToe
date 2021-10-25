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

import de.impf.impfstoffcharge.entity.ImpfstoffchargeTO;
import de.impf.impfstoffcharge.usecase.IChargeErfassen;
import de.impf.impfstoffcharge.usecase.IChargeSuchen;
import de.impf.termin.entity.ImpfungTO;
import de.impf.termin.entity.TerminTO;
import de.impf.termin.usecase.IImpfungPflegen;
import de.impf.termin.usecase.ITerminePflegen;
import de.impf.termin.usecase.ITermineSuchen;

@Named("ImpfungMB")
@RequestScoped
public class ImpfungMB implements Serializable{

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2293814775363405581L;
	
	@Inject 
	IImpfungPflegen impfungPflegenFacade;
	
	@Inject
	ITermineSuchen termineSuchenFacade;
	
	@Inject
	ITerminePflegen terminePflegenFacade;
	
	@Inject
	IChargeSuchen chargeSuchenFacade;
	
	@Inject
	IChargeErfassen chargeErfassenFacade;
	
	//Variablen
	private ImpfungTO aImpfungTO;
	private List<TerminTO> termine;
	private List<ImpfstoffchargeTO> chargen;
	private List<ImpfungTO> impfungen;
	
	public ImpfungMB() {
	}
	
	@PostConstruct
	public void initBean() {
		this.aImpfungTO = new ImpfungTO();
		this.termine = new ArrayList<TerminTO>();
		this.chargen = new ArrayList<ImpfstoffchargeTO>();
		this.impfungen = new ArrayList<ImpfungTO>();
		this.termine = termineSuchenFacade.getAllOpenTermine();
		this.chargen = chargeSuchenFacade.getAllChargen();
		this.impfungen = impfungPflegenFacade.getAllImpfungen();
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
	
	//AW - Impfung anlegen
	public String impfungAnlegen() {
		try {
			impfungPflegenFacade.impfungAnlegen(this.aImpfungTO);
			terminePflegenFacade.setTerminWahrgenommen(this.aImpfungTO.getTerminID());
			sendInfoMessageToUser("Impfung angelegt");
			UserMB.clicked();
			return "IMPFUNG_PFLEGEN_ABBRECHEN";
		} catch (EJBException e) {
			sendErrorMessageToUser("Kann die Impfung nicht anlegen.");
			return "";
		}	
	}
	
	//AW5
	public String impfungUpdaten() {
		try {
			impfungPflegenFacade.impfungUpdaten(this.aImpfungTO);
			chargeErfassenFacade.reduceCharge(this.aImpfungTO.getChargeID());
			sendInfoMessageToUser("Impfung aktualisiert");
			UserMB.clicked();
			return "UPDATE_IMPFUNG_ABBRECHEN";
		} catch (EJBException e) {
			sendErrorMessageToUser("Die Impfung konnte nicht aktualisiert werden.");
			return "";
		}	
	}
	
	//Initialisierungen
	public void ladeTermineAndChargen() {		
		this.termine = termineSuchenFacade.getAllOpenTermine();
		this.chargen = chargeSuchenFacade.getAllChargen();
	}
	
	public void ladeImpfungen() {
		this.impfungen = impfungPflegenFacade.getAllImpfungen();
	}
	
	//Navigation
	public String starteVakzineverwaltung() {
		UserMB.clicked();
		return "VAKZINEVERWALTUNG_MENUE";
	}
	
	@RolesAllowed("ARZT")
	public String starteImpfungErfassen() {
		if (securityContext.isCallerInRole("ARZT")) {
			System.out.println("Anzeigen Impfung erfassen");
			UserMB.clicked();
			return "IMPFUNG_PFLEGEN";
		} else {
			System.out.println("Keine Rechte zum Anzeigen von Impfung erfassen");
			return "STAY_ON_PAGE";	
		}
	}
	
	@RolesAllowed("ARZT")
	public String starteAnzeigeImpfdosenMenge() {
		if (securityContext.isCallerInRole("ARZT")) {
			System.out.println("Anzeigen Impfdosenmenge");
			UserMB.clicked();
			return "ANZEIGE_IMPFDOSENMENGE";
		} else {
			System.out.println("Keine Rechte zum Anzeigen der Impfdosenmengen");
			return "STAY_ON_PAGE";	
		}
	}

	public String vakzineVwAbbruchKlicked() {
		UserMB.clicked();
		return "BACK_TO_HAUPTMENUE";
	}

	public String impfungPflegenAbbruchKlicked() {
		UserMB.clicked();
		return "IMPFUNG_PFLEGEN_ABBRECHEN";
	}
	
	public String impfungListanzeigeAbbruchClicked() {
		UserMB.clicked();
		return "ANSICHT_IMPFUNG_ABBRECHEN";
	}
	
	@RolesAllowed("ARZT")
	public String updateImpfungStart() {
		if (securityContext.isCallerInRole("ARZT")) {
			System.out.println("Anzeigen Update Impfung");
			UserMB.clicked();
			return "UPDATE_IMPFUNG";
		} else {
			System.out.println("Keine Rechte zum Anzeigen von Update Impfung");
			return "STAY_ON_PAGE";	
		}
	}
	
	public String impfungUpdateAbbruchKlicked() {
		UserMB.clicked();
		return "UPDATE_IMPFUNG_ABBRECHEN";
	}
	
	//Getters and Setters
	public ImpfungTO getaImpfungTO() {
		return aImpfungTO;
	}

	public void setaImpfungTO(ImpfungTO aImpfungTO) {
		this.aImpfungTO = aImpfungTO;
	}

	public List<TerminTO> getTermine() {
		return termine;
	}

	public void setTermine(List<TerminTO> termine) {
		this.termine = termine;
	}

	public List<ImpfstoffchargeTO> getChargen() {
		return chargen;
	}

	public void setChargen(List<ImpfstoffchargeTO> chargen) {
		this.chargen = chargen;
	}

	public List<ImpfungTO> getImpfungen() {
		return impfungen;
	}

	public void setImpfungen(List<ImpfungTO> impfungen) {
		this.impfungen = impfungen;
	}

}
