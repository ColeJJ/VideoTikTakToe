package com.videotiktaktoe.app.Gamecenter.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import de.HA2.Patient.entity.impl.Patient;

@Stateless
public class PatientDAO extends GenericDAO<Patient> {
	
	public PatientDAO(){
		super(Patient.class);
	}
	
	public void delete(Patient aPatient){
		super.delete(aPatient.getPnr(), Patient.class);
	}
	
	public List<Patient> findPatientByFirstAndLastName(String vorname, String nachname){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("vorname", vorname);
		parameters.put("nachname", nachname);
		
		return super.findListResult(Patient.FIND_BY_FIRST_AND_LASTNAME, parameters);
		
	}

}
