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
 *
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;
    private UserRepository userRepository;


    private Logger logger=Logger.getLogger(PersonService.class);

    public PersonServiceImpl(PersonRepository personRepository, UserRepository userRepository) {
        this.personRepository=personRepository;
        this.userRepository=userRepository;
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
//    public void updatePerson(Long id, Person person) {
//        Person dbPerson=personRepository.getOne(id);
//
//        dbPerson.getPersonSkills().clear();
//
//
//        if(person.getPersonSkills()!=null) {
//            person.getPersonSkills().forEach(skill -> {
//                dbPerson.getPersonSkills().add(skill);
//                skill.setPerson(dbPerson);
//            });
//        }
//
//        Address dbAddress=dbPerson.getAddress();
//        if(dbAddress!=null) {
//            logger.debug("Address is " + dbAddress.toString());
//            if(person.getAddress()!=null) {
//                dbAddress.setAddress1(person.getAddress().getAddress1());
//                dbAddress.setAddress2(person.getAddress().getAddress2());
//                dbAddress.setCity(person.getAddress().getCity());
//                dbAddress.setState(person.getAddress().getState());
//                dbAddress.setZipCode(person.getAddress().getZipCode());
//            }
//        } else if(person.getAddress()!=null) {
//            dbPerson.setAddress(person.getAddress());
//        }
//
//        updatePersonTrainings(person, dbPerson);
//
//
//        logger.debug("Address is " + (dbPerson.getAddress()==null?"address is null":dbPerson.getAddress().toString()));
//        if (nonNull(person.getBusiness())) {
//            dbPerson.setBusiness(person.getBusiness());
//        }
//        dbPerson.setFirstName(person.getFirstName());
//        dbPerson.setLastName(person.getLastName());
//        dbPerson.setPhone(person.getPhone());
//        dbPerson.setEmail(person.getEmail());
//        dbPerson.setTitle(person.getTitle());
//        dbPerson.setPersonType(person.getPersonType());
//
//        personRepository.saveAndFlush(dbPerson);
//        Person savedPerson=personRepository.getOne(id);
//        logger.debug("Person in DB is " + savedPerson.toString());
//
//
//
//    }

//    @Override
//    public PersonDocument save(Long id, String fileName, MultipartFile file) throws Exception {
//        logger.debug("Enter: PersonServiceImpl.save("+id+", "+fileName+", "+file.getName()+")");
//        Person person=personRepository.getOne(id);
//        Set<PersonDocument> dbPersonDocuments=person.getPersonDocuments();
//        List<PersonDocument> matchedPersonDocuments=dbPersonDocuments.stream().filter(personDocument -> personDocument.getFileName().equals(fileName)).collect(Collectors.toList());
//        PersonDocument document;
//        if(matchedPersonDocuments.size()>0) {
//            document=matchedPersonDocuments.get(0);
//        } else {
//            document=new PersonDocument();
//            dbPersonDocuments.add(document);
//        }
//        document.setDocument(file.getBytes());
//        document.setFileName(fileName);
//        document.setPerson(person);
//        document.setUploadTimestamp(new Date());
//        document.setFileSize((long) document.getDocument().length);
//
//        person=personRepository.saveAndFlush(person);
//        return person.getPersonDocuments().stream().filter(doc -> doc.getFileName().equals(fileName)).findFirst().get();
//    }
//
//    @Override
//    public PersonDocument getDocument(Long id, Long documentId) throws Exception {
//        logger.debug("Enter: PersonServiceImpl.getDocument("+id+", "+documentId+")");
//        Person person=personRepository.getOne(id);
//        return person.getPersonDocuments().stream().filter(doc -> doc.getId().equals(documentId)).findFirst().get();
//    }
//
//    @Override
//    public PersonDocument deleteDocument(Long id, Long documentId) throws Exception {
//        logger.debug("Enter: PersonServiceImpl.deleteDocument("+id+", "+documentId+")");
//        Person person=personRepository.getOne(id);
//        Optional<PersonDocument> document=person.getPersonDocuments().stream().filter(doc -> doc.getId().equals(documentId)).findFirst();
//        if(!document.isPresent()) {
//            return null;
//        }
//        PersonDocument doc=document.get();
//        person.getPersonDocuments().remove(doc);
//        personRepository.saveAndFlush(person);
//        return doc;
//    }

//    private void updatePersonTrainings(Person person, Person dbPerson) {
//        Set<PersonTraining> personTrainings = person.getPersonTrainings();
//        Set<PersonTraining> dbPersonTrainings = new HashSet<>(dbPerson.getPersonTrainings());
//        dbPerson.getPersonTrainings().clear();
//        if (personTrainings == null) {
//            return;
//        }
//        Function<PersonTraining, Boolean> isNewPersonTraining = personTraining -> personTraining.getId() == null;
//        Map<Boolean, List<PersonTraining>> personTrainingsIsNew = personTrainings.stream()
//                .collect(Collectors.groupingBy(isNewPersonTraining));
//
//        List<PersonTraining> newPersonTrainings = personTrainingsIsNew.get(TRUE);
//        if (newPersonTrainings != null) {
//            newPersonTrainings.forEach(newPersonTraining -> {
//                newPersonTraining.setPerson(dbPerson);
//                dbPerson.getPersonTrainings().add(newPersonTraining);
//            });
//        }
//
//        List<PersonTraining> savedPersonTrainings = personTrainingsIsNew.get(FALSE);
//        if (savedPersonTrainings != null) {
//            savedPersonTrainings.forEach(personTraining ->
//                    dbPersonTrainings.stream()
//                            .filter(dbPersonTraining -> personTraining.getId().equals(dbPersonTraining.getId()))
//                            .findFirst().ifPresent(dbPersonTraining -> {
//                                dbPersonTraining.setStartDate(personTraining.getStartDate());
//                                dbPersonTraining.setEndDate(personTraining.getEndDate());
//                                dbPersonTraining.setProgress(personTraining.getProgress());
//                                dbPersonTraining.setHided(personTraining.isHided());
//                                dbPersonTraining.getCompletedTasks().clear();
//                                dbPersonTraining.getCompletedTasks().addAll(personTraining.getCompletedTasks());
//                                dbPerson.getPersonTrainings().add(dbPersonTraining);
//                            }
//                    )
//            );
//        }
//
//    }

    public Person findByEmail(String email){
        return personRepository.findByEmail(email);
    }

    public Person save(Person person){
        return personRepository.saveAndFlush(person);
    }

}