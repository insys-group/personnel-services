/**
 * 
 */
package com.insys.trapps.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insys.trapps.model.Person;
import com.insys.trapps.respositories.PersonRepository;

/**
 * @author msabir
 *
 */
@RepositoryRestController
@RequestMapping(value = "/api/v1/persons/put/1", method = PUT)
@Transactional
public class PersonPutController {
	private Logger logger = LoggerFactory.getLogger(PersonPutController.class);
	@Autowired
	private PersonRepository repository;
	@Autowired
	private EntityManager entityManager;
	
	@RequestMapping
    public @ResponseBody Resource<Person> updatePerson(@RequestBody Person person) {
		logger.debug("Enter: PersonPutController.updatePerson()" + (person==null) + person.toString());
		logger.debug("Person to be saved is " + person.toString());
		
		Person oldPerson=repository.getOne(person.getId());
		oldPerson.getPersonDocuments().clear();
		oldPerson.getPersonSkills().clear();
		oldPerson=repository.save(oldPerson);
		
		//person.setVersion(oldPerson.getVersion());
		
		//repository.saveAndFlush(person);
		
		//entityManager.merge(person);
		//entityManager.flush();
		person.setVersion(oldPerson.getVersion());
		person=repository.saveAndFlush(person);
		person=repository.getOne(person.getId());
		logger.debug("Person in DB is " + person.toString());
		/*
		repository.findAll().stream().forEach(p -> {
			logger.debug("Person in DB is " + p.toString());
		});*/
		
		return new Resource<Person>(person);
	}
}