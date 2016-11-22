package com.insys.trapps.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insys.trapps.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	public Collection<Person> findByFirstName(String firstName);
	public Collection<Person> findByEmail(String email);
}
