package com.yet.spring.core.loggers;

@FunctionalInterface
public interface EventLogger {
	public void logEvent(Event event);
}
