package de.HA2.Patient.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.HA2.Patient.dao.PatientDAO;
import de.HA2.Patient.entity.PatientTO;
import de.HA2.Patient.entity.impl.Patient;
import de.HA2.Patient.usecase.IPatientSuchen;

@Stateless
public class PatientSuchen implements IPatientSuchen {

	@Inject
	PatientDAO patientDAO;
	
	
	@Override
	public List<PatientTO> patientSuchenByName(String vorname, String nachname) {
		
		List<Patient> aList = patientDAO.findPatientByFirstAndLastName(vorname, nachname);
		List<PatientTO> returnList = new ArrayList<PatientTO>();
		for (Patient aPatient : aList) returnList.add(aPatient.toPatientTO());
		return returnList;
	}

}
