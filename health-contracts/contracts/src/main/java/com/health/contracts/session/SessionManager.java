package com.health.contracts.session;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SessionManager implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ConcurrentHashMap<String,Session> session = new ConcurrentHashMap<>();
	
	public static void cache(final String uid,final Session userSession) {
		session.put(uid,userSession);
	}
	
	public static Session get(final String userid) {return session.get(userid);}
	
	
	
}
