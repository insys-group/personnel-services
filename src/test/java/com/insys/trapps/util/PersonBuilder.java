/**
 * 
 */
package com.insys.trapps.util;

import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonType;

/**
 * @author Muhammad Sabir
 *
 */
public class PersonBuilder {
	private Person person;
	
	public static PersonBuilder buildPerson(String firstName, String lastName) {
		PersonBuilder builder=new PersonBuilder();
		builder.person=new Person();
		builder.person.setFirstName(firstName);
		builder.person.setLastName(lastName);
		builder.person.setEmail(firstName.toLowerCase()+"@abc.com");
		builder.person.setPersonType(PersonType.Employee);
		return builder;
	}
	
	public PersonBuilder addEmail(String email) {
		this.person.setEmail(email);
		return this;
	}
	
	public PersonBuilder addPersonType(PersonType personType) {
		this.person.setPersonType(personType);
		return this;
	}
	
	public Person build() {
		return this.person;
	}
}
