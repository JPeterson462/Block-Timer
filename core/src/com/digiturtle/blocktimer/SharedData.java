package com.digiturtle.blocktimer;

import java.util.ArrayList;

public class SharedData {
	
	private ArrayList<Timer> timers;
	
	private int selectedTimer;
	
	private String timerName;
	
	public SharedData() {
		timers = new ArrayList<>();
		selectedTimer = -1;
	}
	
	public int getSelectedTimer() {
		return selectedTimer;
	}

	public void setSelectedTimer(int selectedTimer) {
		this.selectedTimer = selectedTimer;
	}

	public ArrayList<Timer> getTimers() {
		return timers;
	}
	
	public void setTimerName(String timerName) {
		this.timerName = timerName;
	}
	
	public String getTimerName() {
		return timerName;
	}

}
