package com.videotiktaktoe.app.Spielerverwaltung.dao;

import java.util.HashMap;
import java.util.Map;

import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.Wertung;

public class WertungDAO extends GenericDAO<Wertung>{

	public WertungDAO() {
		super(Wertung.class);
	}
	
	public Wertung findWertungByUserID(int userID) {
		Wertung aWertung = new Wertung();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userID", userID);
		
		return super.findOneResult(Wertung.FIND_BY_USERID, parameters);
	}
}
