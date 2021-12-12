package com.videotiktaktoe.app.Gamecenter.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.videotiktaktoe.app.Gamecenter.entity.impl.Spielsession;



@Stateless
public class SpielsessionDAO extends GenericDAO<Spielsession> {
	
	public SpielsessionDAO(){
		super(Spielsession.class);
	}
	
	public void delete(Spielsession aSpielsession){
		super.delete(aSpielsession.getSessionID(), Spielsession.class);
	}
	
	public List<Spielsession> findPatientByFirstAndLastName(String vorname, String nachname){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("vorname", vorname);
		parameters.put("nachname", nachname);
		
		return super.findListResult(Spielsession.FIND_BY_SESSIONID, parameters);
		
	}

}
