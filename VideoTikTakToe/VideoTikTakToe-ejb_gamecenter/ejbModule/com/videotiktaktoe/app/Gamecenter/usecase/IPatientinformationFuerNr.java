package de.HA2.Patient.usecase;

import javax.ejb.Local;

import de.HA2.Patient.entity.PatientTO;

@Local
public interface IPatientinformationFuerNr {

	public PatientTO patientSuchenByNr (int nr);
}
