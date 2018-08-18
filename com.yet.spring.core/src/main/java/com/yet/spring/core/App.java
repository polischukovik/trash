package com.yet.spring.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.loggers.Event;
import com.yet.spring.core.loggers.EventLogger;
import com.yet.spring.core.loggers.EventType;

public class App {
	
	private Client client;
	private EventLogger eventLogger;
		
	public App(Client client, EventLogger eventLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
		
	}

	public static void main(String[] args) {		 
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		context.registerShutdownHook();

		App app = context.getBean(App.class);
		
		app.logEvent("This is only for CacheFile logger");
		app.logEvent("This one is for Console", EventType.INFO);
		app.logEvent("This one is for Console and File", EventType.ERROR);
		
	}
	
	private void logEvent(String msg){
		String message = msg.replaceAll(client.getId(), client.getFullName());
		eventLogger.logEvent(Event.of(message));
	}
	
	private void logEvent(String msg, EventType type){
		String message = msg.replaceAll(client.getId(), client.getFullName());
		eventLogger.logEvent(Event.of(message, type));
	}

}
