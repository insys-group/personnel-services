package com.insys.trapps.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "opportunity")
public class Opportunity implements Serializable {
	private static final long serialVersionUID = -8477329760701271051L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	
	@Column 
	private String comments; 
	
	@OneToMany(mappedBy = "opportunity", targetEntity=OpportunityStep.class, cascade = CascadeType.ALL)
	private Collection<OpportunityStep> steps;
	
	@JoinTable(name="opportunity_contact")
    @ManyToMany(targetEntity = Person.class, cascade = CascadeType.ALL)
    private Collection<Person> contacts;

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

	public Collection<OpportunityStep> getSteps() {
		return steps;
	}

	public void setSteps(Collection<OpportunityStep> steps) {
		this.steps = steps;
	}

	
	public Collection<Person> getContacts() {
		return contacts;
	}

	public void setContacts(Collection<Person> contacts) {
		this.contacts = contacts;
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
		return "Opportunity [id=" + id + ", comments=" + comments + ", steps=" + steps + ", contacts=" + contacts + "]";
	}

}