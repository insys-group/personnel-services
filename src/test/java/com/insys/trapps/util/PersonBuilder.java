package com.insys.trapps.util;

import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonType;

public class PersonBuilder {
	private Person person;
	
	public static PersonBuilder buildPerson(
			String firstName, String lastName, 
			String email, String title, PersonType type) {
		PersonBuilder builder = new PersonBuilder();
		builder.person = Person.builder()
				.firstName(firstName)
				.lastName(lastName)
				.email(email)
				.title(title)
				.personType(type)
				.build();
		return builder;
	}
	
	public Person build() {
		return person;
	}
}
