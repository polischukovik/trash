package com.yet.spring.core.loggers;

import java.util.Date;

public class Event {
	private static int seq = 0;
	
	private int id;
	private String message;
	private Date date;
	private EventType type;
	
	private Event(String message, Date date) {		
		this.id = newId();
		this.message = message;
		this.date = date;
		this.type = null;
	}
	
	private Event(String message, Date date, EventType type) {		
		this.id = newId();
		this.message = message;
		this.date = date;
		this.type = type;
	}
	
	private synchronized int newId(){
		return seq++;
	}

	public String getMessage() {
		return message;
	}
	
	public Date getDate() {
		return date;
	}

	public EventType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", message=" + message + ", date=" + date + "]";
	}
	
	public static Event of(String msg){
		return new Event(msg, new Date());
	}
	
	public static Event of(String msg, EventType type){
		return new Event(msg, new Date(), type);
	}


}
