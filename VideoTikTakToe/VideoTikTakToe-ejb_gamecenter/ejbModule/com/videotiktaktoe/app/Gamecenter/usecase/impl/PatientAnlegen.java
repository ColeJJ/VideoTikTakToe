package de.HA2.Patient.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.HA2.Patient.dao.PatientDAO;
import de.HA2.Patient.entity.PatientTO;
import de.HA2.Patient.entity.impl.Patient;
import de.HA2.Patient.usecase.IPatientAnlegen;

@Stateless
public class PatientAnlegen implements IPatientAnlegen {

	@Inject
	PatientDAO patientDAO;
	


	@SuppressWarnings("unused")
	@Override
	public boolean patientLoeschen(int nummer) {
		Patient aPatient = patientDAO.find(nummer);
		System.out.println("Kunde "+aPatient.getPnr()+" gefunden zum Loeschen");
		if (aPatient == null) {
			return Boolean.FALSE;
		}
		else {
			patientDAO.delete(aPatient);
			return Boolean.TRUE;
		}
	}

	@Override
	public List<PatientTO> getAllPatient() {
		List<Patient> aList = patientDAO.findAll();
		List<PatientTO> returnList = new ArrayList<PatientTO>();
		for (Patient aPatient : aList) returnList.add(aPatient.toPatientTO());
		return returnList;
	}

	@Override
	public void patientAnlegen(PatientTO patientTO) {
		Patient aPatient = new Patient();
		aPatient = patientTO.toPatient();
		patientDAO.save(aPatient);
		
	}


	@Override
	public void patientSpeichern(PatientTO patientTO) {
		System.out.println(patientTO.toString());

		Patient aPatient = patientDAO.find(patientTO.getPnr());
		aPatient.setVorname(patientTO.getVorname());
		aPatient.setNachname(patientTO.getNachname());
		aPatient.setBday(patientTO.getBday());
		aPatient.setMail(patientTO.getMail());
		aPatient.setTelefonnummer(patientTO.getTelefonnummer());
		
		patientDAO.update(aPatient);		
	}

}
