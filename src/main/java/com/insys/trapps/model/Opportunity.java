package com.insys.trapps.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "opportunity")
public class Opportunity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	
	@Column 
	private String comments; 
	
	@OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<OpportunityStep> steps;

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

	public Set<OpportunityStep> getSteps() {
		return steps;
	}

	public void setSteps(Set<OpportunityStep> steps) {
		this.steps = steps;
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
		Opportunity other = (Opportunity) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Opportunity [id=" + id + ", comments=" + comments + "]";
	}

}