/**
 * 
 */
package com.insys.trapps.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonDocument;
import com.insys.trapps.respositories.PersonRepository;

/**
 * @author msabir
 *
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepository repository;
	
	private Logger logger=Logger.getLogger(PersonService.class);
	
	@Override
	public void updatePerson(Long id, Person person) {
		Person dbPerson=repository.getOne(id);
		dbPerson.getPersonDocuments().clear();
		dbPerson.getPersonSkills().clear();
		
		if(person.getPersonDocuments()!=null) {
			person.getPersonDocuments().forEach(doc -> {
				doc.setVersion(1L);
				dbPerson.getPersonDocuments().add(doc);
				doc.setPerson(dbPerson);
			});
		}
		
		if(person.getPersonSkills()!=null) {
			person.getPersonSkills().forEach(skill -> {
				skill.setVersion(1L);
				dbPerson.getPersonSkills().add(skill);
				skill.setPerson(dbPerson);
			});
		}

		Address dbAddress=dbPerson.getAddress();
		if(dbAddress!=null) {
			logger.debug("Address is " + dbAddress.toString());
			if(person.getAddress()!=null) {
				dbAddress.setAddress1(person.getAddress().getAddress1());
				dbAddress.setAddress2(person.getAddress().getAddress2());
				dbAddress.setCity(person.getAddress().getCity());
				dbAddress.setState(person.getAddress().getState());
				dbAddress.setZipCode(person.getAddress().getZipCode());
			}
		} else if(person.getAddress()!=null) {
			person.getAddress().setVersion(1L);
			dbPerson.setAddress(person.getAddress());
		}
		
		if(person.getBusiness()!=null) {
			if(dbPerson.getBusiness()!=null) {
				person.getBusiness().setVersion(dbPerson.getBusiness().getVersion());
			} else {
				person.getBusiness().setVersion(1L);
			}
		}
		logger.debug("Address is " + dbPerson.getAddress().toString());
		dbPerson.setBusiness(person.getBusiness());
		dbPerson.setFirstName(person.getFirstName());
		dbPerson.setLastName(person.getLastName());
		dbPerson.setPhone(person.getPhone());
		dbPerson.setEmail(person.getEmail());
		dbPerson.setTitle(person.getTitle());
		dbPerson.setPersonType(person.getPersonType());
		
		repository.saveAndFlush(dbPerson);
		Person savedPerson=repository.getOne(id);
		logger.debug("Person in DB is " + savedPerson.toString());
	}
	
	@Override
	public void save(Long id, String fileName, MultipartFile file) throws Exception {
		logger.debug("Enter: PersonServiceImpl.save("+id+", "+fileName+", "+file.getName()+")");
		PersonDocument document=new PersonDocument();
		Person person=repository.getOne(id);
		document.setDocument(file.getBytes());
		document.setFileName(fileName);
		document.setPerson(person);
		document.setUploadTimestamp(new Date());
		document.setFileSize(Long.valueOf(document.getDocument().length));
		person.getPersonDocuments().add(document);
		repository.save(person);
	}
}
