package de.HA2.Patient.usecase;

import javax.ejb.Local;

@Local
public interface ITerminRegistrieren {
	
	public void terminHinzufuegen(int pnr, int tnr);
	
}
