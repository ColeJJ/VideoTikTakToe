package de.HA2.Patient.usecase;

import java.util.List;

import javax.ejb.Local;

import de.HA2.Patient.entity.PatientTO;

@Local
public interface IPatientSuchen {
	
	public List<PatientTO> patientSuchenByName (String vorname, String nachname);
	
}
