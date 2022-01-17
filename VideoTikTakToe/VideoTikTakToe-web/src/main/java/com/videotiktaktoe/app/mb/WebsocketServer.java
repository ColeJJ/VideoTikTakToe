package com.videotiktaktoe.app.mb;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class WebsocketServer {
	private static Set<Session> userSessions = Collections.newSetFromMap(new ConcurrentHashMap<Session, Boolean>());
	
    @OnOpen
    public void onOpen(Session userSession) {
    	System.out.println("Neue Verbindung aufgebaut...");
    	userSessions.add(userSession);
    }

    @OnClose
    public void onClose(Session userSession) {
		System.out.println("Verbindung getrennt...");
		userSessions.remove(userSession);

    }

    @OnMessage
    public void onMessage(String message, Session userSession) {
    	broadcast(message);
    }
    
    public static void broadcast(String msg) {
    	System.out.println("Broadcast Nachricht an alle:" + msg);
    	for (Session session : userSessions) {
    	    session.getAsyncRemote().sendText("Re: " + msg);
    	}
    }
}
