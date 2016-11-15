package com.insys.trapps.model;

import java.util.Date;
import java.util.List;

public class Client {
	private long id;
	private String name;
	private String description;
	private List<String> locations;
	private List<Date> dates;
	
	public Client(String name) {
		this.name = name;
	}
	
	

	public Client(long id, String name, String description, List<String> locations, List<Date> dates) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.locations = locations;
		this.dates = dates;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Client [name=" + name + "]";
	}
}
