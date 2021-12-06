package de.HA2.Patient.usecase.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.HA2.Patient.dao.PatientDAO;
import de.HA2.Patient.entity.PatientTO;
import de.HA2.Patient.usecase.IPatientinformationFuerNr;

@Stateless
public class PatientinformationFuerNr implements IPatientinformationFuerNr {

	@Inject
	PatientDAO patientDAO;
	
	@Override
	public PatientTO patientSuchenByNr(int nr) {
		return patientDAO.find(nr).toPatientTO();
	}

}
