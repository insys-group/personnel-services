package com.insys.trapps.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Brad Starkenberg
 */
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String email;
	@NotNull
	private int phoneNumber;
	@OneToMany(mappedBy="person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PersonSkill> skills;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Address address;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Document> documents;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the skills
	 */
	public Set<PersonSkill> getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(Set<PersonSkill> skills) {
		this.skills = skills;
	}
	
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the documents
	 */
	public Set<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	/**
	 * Convenience method (remember to save after adding skill)
	 * @param skill
	 * @param level
	 */
	public void addSkill(Skill skill, int level) {
		PersonSkill newSkill = new PersonSkill();
		newSkill.setPerson(this);
		newSkill.setSkill(skill);
		newSkill.setLevel(level);
		if(skills == null)
			skills = new HashSet<PersonSkill>();
		skills.add(newSkill);
	}
	
	/**
	 * Convenience method (remember to save after adding a document)
	 * @param doc
	 */
	public void addDocument(Document doc) {
		if (documents == null)
			documents = new HashSet<Document>();
		documents.add(doc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(15, 9).append(firstName).append(lastName).append(email).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Person rhs = (Person) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(firstName, rhs.getFirstName())
				.append(lastName, rhs.getLastName()).append(email, rhs.getEmail()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
