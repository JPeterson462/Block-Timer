package com.digiturtle.blocktimer;

public class Timer {
	
	private String name, internalId;
	
	public Timer(String name, String internalId) {
		this.name = name;
		this.internalId = internalId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInternalId() {
		return internalId;
	}

}
