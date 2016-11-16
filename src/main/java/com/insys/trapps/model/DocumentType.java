/**
 * 
 */
package com.insys.trapps.model;

public enum DocumentType {
	Resume("Resume"),
	INSYSPROFILE("INSYS Profile");
	
	private String documentType;
	
	DocumentType(String documentType) {
		this.documentType=documentType;
	}
}
