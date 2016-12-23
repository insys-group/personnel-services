/**
 * 
 */
package com.insys.trapps.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.insys.trapps.model.Person;
import com.insys.trapps.service.PersonService;

/**
 * @author msabir
 *
 */
@RepositoryRestController
@RequestMapping("/api/v1")
public class PersonPutController {
	private Logger logger = LoggerFactory.getLogger(PersonPutController.class);
	@Autowired
	private PersonService service;
	
	@PutMapping(value = "/persons/put/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
		logger.debug("Enter: PersonPutController.updatePerson()" + (person==null) + person.toString());
		logger.debug("Person to be saved is " + person.toString());
		service.updatePerson(id, person);
	}
	
	@PostMapping(value="/persondocuments/{id}/{fileName}")
	@ResponseStatus(HttpStatus.CREATED)
    public void handleFileUpload(
    		@PathVariable("id") Long id, 
    		@PathVariable("fileName") String fileName, 
    		@RequestParam("file") MultipartFile file) throws Exception {
        service.save(id, fileName, file);
    }
}