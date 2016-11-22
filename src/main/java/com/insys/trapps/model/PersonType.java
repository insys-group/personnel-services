package com.insys.trapps.model;


/**
 * @author Muhammad Sabir
 * This class serves as the lookup value for person type in Person class
 */
public enum PersonType {
	EMPLOYEE("Employee"),
	CANDIDATE("Candidate"),
	PIVOTAL("Pivotal"),
	VENDOR("Vendor");
	
	private String personType;
	
	PersonType(String personType) {
		this.personType=personType;
	}
}
