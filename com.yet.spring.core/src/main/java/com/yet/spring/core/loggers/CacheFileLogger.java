package com.yet.spring.core.loggers;

import java.util.ArrayList;
import java.util.List;

public class CacheFileLogger extends FileEventLogger{
	private int chacheSize;
	List<Event> cache = new ArrayList<>();
		
	public CacheFileLogger(String fileName, int cacheSize) {
		super(fileName);
		this.chacheSize = cacheSize;
	}

	public void logEvent(Event event) {
		if(cache.size() < chacheSize){
			cache.add(event);
		} else {
			writeFromCache();
			cache.clear();
		}
	}

	private void writeFromCache() {
		for(Event e : cache){
			super.logEvent(e);
		}
	}
	
	private void destroy(){
		if(!cache.isEmpty()){
			writeFromCache();
		}
	}

}
