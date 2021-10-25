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
import de.impf.impfstoffcharge.usecase.IAnzeigeImpfdosenMenge;
import de.impf.impfstoffcharge.usecase.IChargeErfassen;
import de.impf.impfstoffcharge.usecase.IChargeSuchen;

@Named("ImpfstoffchargeMB")
@RequestScoped
public class ImpfstoffchargeMB implements Serializable{

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6454512266816010697L;
	
	@Inject
	IAnzeigeImpfdosenMenge anzeigeImpfdosenMengeFacade;
	
	@Inject
	IChargeErfassen chargeErfassenFacade;
	
	@Inject
	IChargeSuchen chargeSuchenFacade;
	
	//Variablen
	private UserMB user;
	private ImpfstoffchargeTO aChargeTO;
	private List<ImpfstoffchargeTO> herstellerListe;
	private String[] hersteller = {"Biontech", "AstraZeneca", "Moderna"};
	
	public String[] getHersteller() {
		return hersteller;
	}

	public void setHersteller(String[] hersteller) {
		this.hersteller = hersteller;
	}

	public ImpfstoffchargeMB() {
	}
	
	@PostConstruct
	public void initBean() {
		this.aChargeTO = new ImpfstoffchargeTO();
		this.herstellerListe = new ArrayList<ImpfstoffchargeTO>();
		this.herstellerListe = anzeigeImpfdosenMengeFacade.anzeigeImpfdosenNachHersteller();
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
	
	//AW - Impfstoffcharge erfassen
	public String chargeErfassen() {
		try {
			chargeErfassenFacade.chargeErfassen(this.aChargeTO);
			sendInfoMessageToUser("Impfstoffcharge erfasst");
			UserMB.clicked();
			return "IMPFSTOFFCHARGE_ERFASSEN_ABBRECHEN";
		} catch (EJBException e) {
			sendErrorMessageToUser("Kann die Impfstoffcharge nicht erfassen.");
			return "";
		}
		
	}
	
	//AW - Liste mit Impfstoffmengen nach Hersteller, holt List mit den Informationen 
	public void anzeigeImpfdosenNachHersteller() {
		this.herstellerListe = anzeigeImpfdosenMengeFacade.anzeigeImpfdosenNachHersteller();
	}
	
	//Navigation
	public String starteVakzineverwaltung() {
		UserMB.clicked();
		return "VAKZINEVERWALTUNG_MENUE";
	}
	
	public String chargeErfassenAbbruchKlicked() {
		UserMB.clicked();
		return "IMPFSTOFFCHARGE_ERFASSEN_ABBRECHEN";
	}
	
	@RolesAllowed("MATERIALMANAGER")
	public String starteChargeErfassen() {
		if (securityContext.isCallerInRole("MATERIALMANAGER")) {
			System.out.println("Anzeigen Charge erfassen");
			UserMB.clicked();
			return "IMPFCHARGE_ERFASSEN";
		} else {
			System.out.println("Keine Rechte zum Anzeigen von Charge erfassen");
			return "STAY_ON_PAGE";	
		}
	}
	
	@RolesAllowed("MATERIALMANAGER")
	public String starteAnzeigeImpfdosenMenge() {
		if (securityContext.isCallerInRole("MATERIALMANAGER")) {
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
	
	public String chargeListeAbbruchKlicked() {
		UserMB.clicked();
		return "ANZEIGE_IMPFDOSENMENGE_ABBRECHEN";
	}

	public ImpfstoffchargeTO getaChargeTO() {
		return aChargeTO;
	}

	public void setaChargeTO(ImpfstoffchargeTO aChargeTO) {
		this.aChargeTO = aChargeTO;
	}

	public List<ImpfstoffchargeTO> getHerstellerListe() {
		return herstellerListe;
	}

	public void setHerstellerListe(List<ImpfstoffchargeTO> herstellerListe) {
		this.herstellerListe = herstellerListe;
	}

}
