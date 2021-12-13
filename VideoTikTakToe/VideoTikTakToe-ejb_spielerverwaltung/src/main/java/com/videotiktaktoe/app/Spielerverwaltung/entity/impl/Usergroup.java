package com.videotiktaktoe.app.Spielerverwaltung.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VTTT_usergroup")
public class Usergroup {

	//PK
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Variables
	@Column(unique=true, nullable=false)
	private String groupname;
	
	//Constructor
	public Usergroup() {}

	//Getters and Setters
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
