package com.insys.trapps.model;

public enum ProgressType {
	
	NotStarted("Not-started"),
	InProgress("In-Progress"),
	Completed("Completed");
	
	private String display;

	private ProgressType(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}
	
}
