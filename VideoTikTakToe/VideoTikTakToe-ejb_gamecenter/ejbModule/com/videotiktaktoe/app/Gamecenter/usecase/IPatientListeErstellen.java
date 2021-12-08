package de.HA2.Patient.usecase;

import java.util.Collection;

import javax.ejb.Local;

import de.HA2.Patient.entity.PatientTO;

@Local
public interface IPatientListeErstellen {
	public Collection<PatientTO> patientListeAusgeben();

}
