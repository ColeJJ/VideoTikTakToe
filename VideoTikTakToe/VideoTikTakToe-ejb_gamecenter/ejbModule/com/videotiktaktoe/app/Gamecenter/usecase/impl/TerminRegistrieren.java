package de.HA2.Patient.usecase.impl;


import javax.ejb.Stateless;
import javax.inject.Inject;

import de.HA2.Patient.dao.PatientDAO;
import de.HA2.Patient.entity.impl.Patient;
import de.HA2.Patient.usecase.ITerminRegistrieren;

@Stateless
public class TerminRegistrieren implements ITerminRegistrieren{

	@Inject
	PatientDAO patientDAO;
	
	@Override
	public void terminHinzufuegen(int pnr, int tnr) {
		Patient aPatient = patientDAO.find(pnr);
		aPatient.addTermin(tnr);
		patientDAO.save(aPatient);
	}
}
