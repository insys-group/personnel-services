package com.insys.trapps.respositories;

import org.springframework.data.repository.CrudRepository;

import com.insys.trapps.model.Person;

/**
 * @author Brad Starkenberg
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

}
