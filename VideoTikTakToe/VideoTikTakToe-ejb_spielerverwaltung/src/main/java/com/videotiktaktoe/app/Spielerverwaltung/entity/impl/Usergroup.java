package com.videotiktaktoe.app.Spielerverwaltung.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.videotiktaktoe.app.Spielerverwaltung.entity.UsergroupTO;

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
	
	public Usergroup(int id, String groupname) {
		this.id = id;
		this.groupname = groupname;
	}
	
	public UsergroupTO toUsergroupTO() {
		UsergroupTO aGroupTO = new UsergroupTO();
		aGroupTO.setId(this.getId());
		aGroupTO.setGroupname(this.getGroupname());
		return aGroupTO;	
	}

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
