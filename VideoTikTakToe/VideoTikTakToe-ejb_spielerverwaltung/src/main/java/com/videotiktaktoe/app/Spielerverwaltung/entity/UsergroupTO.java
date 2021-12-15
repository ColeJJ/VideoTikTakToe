package com.videotiktaktoe.app.Spielerverwaltung.entity;

import java.io.Serializable;

import com.videotiktaktoe.app.Spielerverwaltung.entity.impl.Usergroup;

public class UsergroupTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6582748394477190181L;
	
	//Variables
	private int id;
	private String groupname;
	
	//Konstruktor
	public UsergroupTO() {}
	
	public Usergroup toUsergroup() {
		Usergroup group = new Usergroup(
				this.getId(),
				this.getGroupname()
				);
		return group;
	}
	
	public String toString() {
		return "Groupname: " + this.getGroupname();
	}

	//Getter and Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
}
