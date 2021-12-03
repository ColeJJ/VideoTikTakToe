package com.videotiktaktoe.app.VideoTikTakToe.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VTTT_User")
@NamedQuery(name="User.findUserByNickname", query="select u from User u where u.username = :username")
public class User {

}
