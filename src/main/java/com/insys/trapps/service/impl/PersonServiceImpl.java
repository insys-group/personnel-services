package com.insys.trapps.service.impl;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.PersonTraining;
import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.person.PersonDocument;
import com.insys.trapps.model.security.User;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.UserRepository;
import com.insys.trapps.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;

/**
 * @author msabir
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;
    private UserRepository userRepository;


    private Logger logger = Logger.getLogger(PersonService.class);

    public PersonServiceImpl(PersonRepository personRepository, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Person findPerson(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    public User findUser(String username) {
        return userRepository.getOne(username);
    }

//    @Override
//    public PersonDocument save(Long id, String fileName, MultipartFile file) throws Exception {
//        logger.debug("Enter: PersonServiceImpl.save(" + id + ", " + fileName + ", " + file.getName() + ")");
//        Person person = personRepository.getOne(id);
//        Set<PersonDocument> dbPersonDocuments = person.getPersonDocuments();
//        List<PersonDocument> matchedPersonDocuments = dbPersonDocuments.stream().filter(personDocument -> personDocument.getFileName().equals(fileName)).collect(Collectors.toList());
//        PersonDocument document;
//        if (matchedPersonDocuments.size() > 0) {
//            document = matchedPersonDocuments.get(0);
//        } else {
//            document = new PersonDocument();
//            dbPersonDocuments.add(document);
//        }
//        document.setDocument(file.getBytes());
//        document.setFileName(fileName);
//        document.setPerson(person);
//        document.setUploadTimestamp(new Date());
//        document.setFileSize((long) document.getDocument().length);
//
//        person = personRepository.saveAndFlush(person);
//        return person.getPersonDocuments().stream().filter(doc -> doc.getFileName().equals(fileName)).findFirst().get();
//    }
//
//    @Override
//    public PersonDocument getDocument(Long id, Long documentId) throws Exception {
//        logger.debug("Enter: PersonServiceImpl.getDocument(" + id + ", " + documentId + ")");
//        Person person = personRepository.getOne(id);
//        return person.getPersonDocuments().stream().filter(doc -> doc.getId().equals(documentId)).findFirst().get();
//    }
//
//    @Override
//    public PersonDocument deleteDocument(Long id, Long documentId) throws Exception {
//        logger.debug("Enter: PersonServiceImpl.deleteDocument(" + id + ", " + documentId + ")");
//        Person person = personRepository.getOne(id);
//        Optional<PersonDocument> document = person.getPersonDocuments().stream().filter(doc -> doc.getId().equals(documentId)).findFirst();
//        if (!document.isPresent()) {
//            return null;
//        }
//        PersonDocument doc = document.get();
//        person.getPersonDocuments().remove(doc);
//        personRepository.saveAndFlush(person);
//        return doc;
//    }

    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person save(Person person) {
        return personRepository.saveAndFlush(person);
    }

}