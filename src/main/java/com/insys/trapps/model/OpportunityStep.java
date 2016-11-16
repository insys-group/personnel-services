package com.insys.trapps.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "opportunity_step")
public class OpportunityStep {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	
	@Column(nullable = false) 
	private String comments; 

	@Column(nullable = false) 
	private Timestamp stepTimestamp;
	
	@ManyToOne
	@JoinColumn(name = "opportunity_id")
	private Opportunity opportunity;

	public OpportunityStep() {
		
	}
	
	public OpportunityStep(String comments, Timestamp stepTimestamp) {
		this.comments=comments;
		this.stepTimestamp=stepTimestamp;
	}
	
	public OpportunityStep(Opportunity opportunity, String comments, Timestamp stepTimestamp) {
		this.opportunity=opportunity;
		this.comments=comments;
		this.stepTimestamp=stepTimestamp;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Timestamp getStepTimestamp() {
		return stepTimestamp;
	}

	public void setStepTimestamp(Timestamp stepTimestamp) {
		this.stepTimestamp = stepTimestamp;
	}

	public Opportunity getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpportunityStep other = (OpportunityStep) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OpportunityStep [id=" + id + ", comments=" + comments + ", stepTimestamp=" + stepTimestamp + "]";
	}
	
	
}
