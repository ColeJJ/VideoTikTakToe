package de.HA2.Patient.usecase;

import java.util.List;

import javax.ejb.Local;

import de.HA2.Patient.entity.PatientTO;

@Local
public interface IPatientAnlegen {


	public boolean patientLoeschen (int nummer);
	public List<PatientTO> getAllPatient();
	public void patientAnlegen(PatientTO patientTO);
	public void patientSpeichern(PatientTO patientTO);
}
