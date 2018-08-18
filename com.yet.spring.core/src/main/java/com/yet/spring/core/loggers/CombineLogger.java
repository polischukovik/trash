package com.yet.spring.core.loggers;

import java.util.List;

public class CombineLogger implements EventLogger{

	List<EventLogger> loggers;
	
	public CombineLogger(List<EventLogger> loggers) {
		this.loggers = loggers;
	}
	
	public void logEvent(Event event){
		loggers.forEach(l -> l.logEvent(event));
	}

}
