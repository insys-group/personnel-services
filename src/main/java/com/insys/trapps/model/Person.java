package com.insys.trapps.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements Serializable {
	private static final long serialVersionUID = 9002720247703144895L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column
	private String phoneNumber;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private PersonType personType;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PersonSkill> skills;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Document> documents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	/**
	 * @return the skills
	 */
	public Set<PersonSkill> getSkills() {
		return skills;
	}

	/**
	 * @param skills
	 *            the skills to set
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
	 * @param address
	 *            the address to set
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
	 * @param documents
	 *            the documents to set
	 */
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	/**
	 * Convenience method (remember to save after adding skill)
	 * 
	 * @param skill
	 * @param level
	 */
	public void addSkill(Skill skill, int level) {
		PersonSkill newSkill = new PersonSkill();
		newSkill.setPerson(this);
		newSkill.setSkill(skill);
		newSkill.setLevel(level);
		if (skills == null)
			skills = new HashSet<PersonSkill>();
		skills.add(newSkill);
	}

	/**
	 * Convenience method (remember to save after adding a document)
	 * 
	 * @param doc
	 */
	public void addDocument(Document doc) {
		if (documents == null)
			documents = new HashSet<Document>();
		documents.add(doc);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Person other = (Person) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phoneNumber
				+ ", email=" + email + ", personType=" + personType + "]";
	}

}
