package com.yet.spring.core.loggers;

import java.util.Map;

public class LogDispetcher implements EventLogger {
	private Map<EventType, EventLogger> eventMapping;
	private EventLogger def;
	
	public LogDispetcher(Map<EventType, EventLogger> eventMapping, EventLogger def) {		
		this.eventMapping = eventMapping;
		this.def = def;
	}

	@Override
	public void logEvent(Event event) {
		if(eventMapping.containsKey(event.getType())){
			eventMapping.get(event.getType()).logEvent(event);
		} else {
			def.logEvent(event);
		}
	}

}
