package com.insys.trapps.model;


/**
 * @author Muhammad Sabir
 * This class serves as the lookup value for person type in Person class
 */
public enum PersonType {
	Employee("Employee"),
	Candidate("Candidate"),
	Pivotal("Pivotal"),
	Vendor("Vendor");
	
	private String personType;
	
	PersonType(String personType) {
		this.personType=personType;
	}
}
