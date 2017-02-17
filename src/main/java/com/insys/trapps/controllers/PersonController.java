/**
 * 
 */
package com.insys.trapps.controllers;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonDocument;
import com.insys.trapps.service.PersonService;

/**
 * @author msabir
 *
 */
@RepositoryRestController
@RequestMapping("/api/v1")
public class PersonController {
	private Logger logger = LoggerFactory.getLogger(PersonController.class);
	@Autowired
	private PersonService service;
	
	@PutMapping(value = "/persons/put/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
		logger.debug("Enter: PersonPutController.updatePerson()" + (person==null) + person.toString());
		logger.debug("Person to be saved is " + person.toString());
		service.updatePerson(id, person);
	}

	@PostMapping(value = "/persons")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Person savePerson(@RequestBody Person person) {
		logger.debug("Enter: PersonPutController.savePerson()");
		logger.debug("Person to be saved is " + person.toString());
		return service.savePerson(person);
	}
	
	@PostMapping(value="/persondocuments/{id}/documents")
    public ResponseEntity<Resource<PersonDocument>> handleFileUpload(
    		@PathVariable("id") Long id, 
    		@RequestHeader("x-filename") String fileName, 
    		@RequestParam("file") MultipartFile file) throws Exception {
		PersonDocument document;
		try{
			document=service.save(id, fileName, file);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(new Resource<PersonDocument>(document));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
		}
    }
	
	@DeleteMapping(value="/persondocuments/{id}/{documentId}")
    public ResponseEntity<PersonDocument> deleteDocument(
    		@PathVariable("id") Long id,
    		@PathVariable("documentId") Long documentId) throws Exception {
		try{
			PersonDocument document=service.deleteDocument(id, documentId);
			if(document==null) {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(null);
			}
			document.setPerson(null);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(document);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
		}
    }

	@GetMapping(value="/persondocuments/{id}/{documentId}")
    public void handleFileDownload(
    		HttpServletResponse response,
    		@PathVariable("id") Long id, 
    		@PathVariable("documentId") Long documentId) throws Exception {
		PersonDocument document=service.getDocument(id, documentId);
		response.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-download");
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+document.getFileName()+"\"");
		response.getOutputStream().write(document.getDocument());
    }
}