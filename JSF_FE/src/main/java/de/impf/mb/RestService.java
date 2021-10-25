package de.impf.mb;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.impf.impfstoffcharge.entity.ImpfstoffchargeTO;
import de.impf.impfstoffcharge.usecase.IAnzeigeImpfdosenMenge;
import de.impf.patient.entity.PatientTO;
import de.impf.patient.usecase.IPatientenSuchen;
import de.impf.termin.entity.TerminTO;
import de.impf.termin.usecase.ITermineSuchen;

@RequestScoped
@Path("/patient")
public class RestService {

	@Inject
	ITermineSuchen impftermineFuerPatientNr;
	
	@Inject
	IPatientenSuchen patientenSuchen;
	
	@Inject 
	IAnzeigeImpfdosenMenge anzeigeImpfdosenMenge;
	
	/*
	 *  http://localhost:8080/HA2_TorbenUnland_JSF_903367/rest/patient/getImpftermine/{nr}
	 */
    @GET
    @Path("getImpftermine/{nr}")
    @Produces(MediaType.TEXT_HTML)
    @PermitAll
    public String getImpftermine(@PathParam("nr") int nr) {
    	System.out.println("getImpftermine("+nr+") called");
    	PatientTO aPatient = patientenSuchen.findByID(nr);
    	List<TerminTO> terminTOList = impftermineFuerPatientNr.impftermineFuerPatientNr(nr);
    	String response = "<h1>Liste der Termine fuer: "+aPatient.getVorname()+" "+aPatient.getName()+"</h1>"+"<br/>"+"<ul>";
    	for(TerminTO aTerminTO:terminTOList) {
    		response += "<li>"+aTerminTO.getDatum()+", "+aTerminTO.getUhrzeit()+"</li>" + "<br />";
    	}
    	response += "</ul>";
    	return response;
    }
    
    /*
	 *  http://localhost:8080/HA2_TorbenUnland_JSF_903367/rest/patient/getBestaende
	 */
    @GET
    @Path("getBestaende")
    @Produces(MediaType.TEXT_HTML)
    @PermitAll
    public String getBestaende() {
    	System.out.println("getBestaende called");
    	List<ImpfstoffchargeTO> chargenTOListe = anzeigeImpfdosenMenge.anzeigeImpfdosenNachHersteller();
    	String response = "<h1>Impfbestaende</h1>"+"<br/>"+"<table>";
    	for(ImpfstoffchargeTO aChargeTO:chargenTOListe) {
    		response += "<tr><th>"+aChargeTO.getHersteller()+"</th>"+
    				"<th>"+aChargeTO.getAnzahl()+"</th>" + "</tr>";
    	}
    	response += "</table>";
    	return response;
    }
}
