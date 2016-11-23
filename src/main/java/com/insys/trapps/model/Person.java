package com.insys.trapps.model;

import lombok.*;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@AllArgsConstructor
@NoArgsConstructor
public class Person extends AbstractEntity{

	@Getter
	@Setter
	@NonNull
	@Column (name = "FIRST_NAME" , nullable = false)
	private String firstName;

	@Getter
	@Setter
	@NonNull
	@Column (name = "LAST_NAME" ,nullable = false)
	private String lastName;

	@Getter
	@Setter
	@Column (name="PHONE")
	private String phone;

	@Getter
	@Setter
	@NonNull
	@Column (name="EMAIL", nullable = false)
	private String email;

	@Getter
	@Setter
	@NonNull
	@Column (name="PERSON_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private PersonType personType;	
	
	//@OneToOne
	//private Address address;

	@Getter
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PersonDocument> documents;

	@ManyToOne
	@JoinColumn(name = "person")
	private Business business;
}
