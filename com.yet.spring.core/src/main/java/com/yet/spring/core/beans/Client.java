package com.yet.spring.core.beans;

public class Client {
	private String id;
	private String fullName;
	private String title;
		
	public Client(String id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", fullName=" + fullName + ", title=" + title + "]";
	}	
	
	

}
