package com.insys.trapps.model;

public class Opportunity {
	private String company;
	private String state;
	private String stage;
	private String insysRep;
	private String pivotalRep;
	private String pivotalLabsRep;
	private String description;
	private String nextSteps;
	private String relationship;
	
	// resources
	private String pcfArchitect;
	private String architect;
	private String developer;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getInsysRep() {
		return insysRep;
	}
	public void setInsysRep(String insysRep) {
		this.insysRep = insysRep;
	}
	public String getPivotalRep() {
		return pivotalRep;
	}
	public void setPivotalRep(String pivotalRep) {
		this.pivotalRep = pivotalRep;
	}
	public String getPivotalLabsRep() {
		return pivotalLabsRep;
	}
	public void setPivotalLabsRep(String pivotalLabsRep) {
		this.pivotalLabsRep = pivotalLabsRep;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNextSteps() {
		return nextSteps;
	}
	public void setNextSteps(String nextSteps) {
		this.nextSteps = nextSteps;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getPcfArchitect() {
		return pcfArchitect;
	}
	public void setPcfArchitect(String pcfArchitect) {
		this.pcfArchitect = pcfArchitect;
	}
	public String getArchitect() {
		return architect;
	}
	public void setArchitect(String architect) {
		this.architect = architect;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
}
