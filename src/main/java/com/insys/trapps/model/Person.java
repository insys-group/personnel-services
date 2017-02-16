package com.insys.trapps.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PERSON")
@EqualsAndHashCode(of = { "email" }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(of = {"firstName", "lastName", "phone", "email", "title"})
public class Person implements Serializable {
	private static final long serialVersionUID = 7055994680040943127L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Getter
	@Setter
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Getter
	@Setter
	@Column(name = "PHONE")
	private String phone;

	@Getter
	@Setter
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Getter
	@Setter
	@Column(name = "TITLE", nullable = true)
	private String title;

	@Getter
	@Setter
	@Column(name = "PERSON_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private PersonType personType;

	@Getter
	@Setter
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", nullable = true, insertable = true, updatable = true)
	private Address address;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "BUSINESS_ID")
    private Business business;

	@Getter
	@Setter
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<PersonDocument> personDocuments = new HashSet<>();

	@Getter
	@Setter
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<PersonSkill> personSkills = new HashSet<>();

	@Getter
	@Setter
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	//@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.EAGER)
	/*@JoinTable(name = "PERSON_TRAINING"
			, joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
			, inverseJoinColumns = @JoinColumn(name = "TRAINING_ID", referencedColumnName = "ID"))*/
	@JsonManagedReference (value="person-training")
	private Set<PersonTraining> personTrainings = new HashSet<>();

}
