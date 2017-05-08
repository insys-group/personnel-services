package com.insys.trapps.model.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.PersonTraining;
import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.model.security.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERSON")
@EqualsAndHashCode(of = { "email" }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(of = {"firstName", "lastName", "phone", "email", "title"})
@JsonIgnoreProperties(ignoreUnknown = true)
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
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "BUSINESS_ID")
	private Business business;

	@Getter
	@Setter
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private Set<PersonDocument> personDocuments = new HashSet<>();

	@Getter
	@Setter
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private Set<PersonSkill> personSkills = new HashSet<>();

	@Getter
	@Setter
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference ("person-training")
	private Set<PersonTraining> personTrainings = new HashSet<>();

}
