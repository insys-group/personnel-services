package com.insys.trapps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.Skill;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.SkillRepository;

/**
 * @author Brad Starkenberg
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SkillRepository skillRepository;

    /**
     * @param id
     * @return the person requested
     */
    @RequestMapping(path = "/{id}", produces = "application/*", method = RequestMethod.GET)
    public Person getPerson(@PathVariable Long id) {
	return personRepository.findOne(id);
    }

    /**
     * @return the person created
     */
    @RequestMapping(path = "/tester", method = RequestMethod.POST)
    public Person createTestPerson() {
	Person brad = new Person();
	brad.setFirstName("Brad");
	brad.setLastName("Starkenberg");
	brad.setEmail("brad@starkenberg.net");
	brad.setPhoneNumber(2135551212);
	Skill skill = new Skill();
	skill.setSkill("Spring");
	skillRepository.save(skill);
	brad.addSkill(skill, 9);
	brad.setAddress(createAddress());
	log.debug("Creating test person");
	brad = personRepository.save(brad);
	return brad;
    }

    /**
     * @return a bogus address for testing
     */
    private Address createAddress() {
	Address address = new Address();
	address.setAddress1("1234 Soaring Hawk Rd");
	address.setCity("Bentonville");
	address.setState("AR");
	address.setZipCode("72712");
	return address;
    }
}
