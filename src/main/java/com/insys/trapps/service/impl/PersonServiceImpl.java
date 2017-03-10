/**
 *
 */
package com.insys.trapps.service.impl;

import com.insys.trapps.model.*;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.TrainingRepository;
import com.insys.trapps.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author msabir
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private TrainingRepository trainingRepository;

    private Logger logger = Logger.getLogger(PersonService.class);

    @Override
    public void updatePerson(Long id, Person person) {
        logger.debug("Updates person with trainings: " + person.getPersonTrainings());
        Person dbPerson = repository.getOne(id);

        dbPerson.getPersonSkills().clear();
        if (person.getPersonSkills() != null) {
            person.getPersonSkills().forEach(skill -> {
                dbPerson.getPersonSkills().add(skill);
                skill.setPerson(dbPerson);
            });
        }

        Address dbAddress = dbPerson.getAddress();
        if (dbAddress != null) {
            logger.debug("Address is " + dbAddress.toString());
            if (person.getAddress() != null) {
                dbAddress.setAddress1(person.getAddress().getAddress1());
                dbAddress.setAddress2(person.getAddress().getAddress2());
                dbAddress.setCity(person.getAddress().getCity());
                dbAddress.setState(person.getAddress().getState());
                dbAddress.setZipCode(person.getAddress().getZipCode());
            }
        } else if (person.getAddress() != null) {
            dbPerson.setAddress(person.getAddress());
        }

        updatePersonTrainings(person, dbPerson);

        logger.debug("Address is " + (dbPerson.getAddress() == null ? "address is null" : dbPerson.getAddress().toString()));
        dbPerson.setBusiness(person.getBusiness());
        dbPerson.setFirstName(person.getFirstName());
        dbPerson.setLastName(person.getLastName());
        dbPerson.setPhone(person.getPhone());
        dbPerson.setEmail(person.getEmail());
        dbPerson.setTitle(person.getTitle());
        dbPerson.setPersonType(person.getPersonType());

        Person savedPerson = repository.saveAndFlush(dbPerson);

        logger.debug("Person in DB is " + savedPerson.toString());
    }

    @Override
    public PersonDocument save(Long id, String fileName, MultipartFile file) throws Exception {
        logger.debug("Enter: PersonServiceImpl.save(" + id + ", " + fileName + ", " + file.getName() + ")");
        Person person = repository.getOne(id);
        Set<PersonDocument> dbPersonDocuments = person.getPersonDocuments();
        List<PersonDocument> matchedPersonDocuments = dbPersonDocuments.stream().filter(personDocument -> personDocument.getFileName().equals(fileName)).collect(Collectors.toList());
        PersonDocument document = null;
        if (matchedPersonDocuments.size() > 0) {
            document = matchedPersonDocuments.get(0);
        } else {
            document = new PersonDocument();
            dbPersonDocuments.add(document);
        }
        document.setDocument(file.getBytes());
        document.setFileName(fileName);
        document.setPerson(person);
        document.setUploadTimestamp(new Date());
        document.setFileSize((long) document.getDocument().length);

        person = repository.saveAndFlush(person);
        return person.getPersonDocuments().stream().filter(doc -> doc.getFileName().equals(fileName)).findFirst().get();
    }

    @Override
    public PersonDocument getDocument(Long id, Long documentId) throws Exception {
        logger.debug("Enter: PersonServiceImpl.getDocument(" + id + ", " + documentId + ")");
        Person person = repository.getOne(id);
        return person.getPersonDocuments().stream().filter(doc -> doc.getId().equals(documentId)).findFirst().get();
    }

    @Override
    public PersonDocument deleteDocument(Long id, Long documentId) throws Exception {
        logger.debug("Enter: PersonServiceImpl.deleteDocument(" + id + ", " + documentId + ")");
        Person person = repository.getOne(id);
        Optional<PersonDocument> document = person.getPersonDocuments().stream().filter(doc -> doc.getId().equals(documentId)).findFirst();
        if (!document.isPresent()) {
            return null;
        }
        PersonDocument doc = document.get();
        person.getPersonDocuments().remove(doc);
        repository.saveAndFlush(person);
        return doc;
    }

    private void updatePersonTrainings(Person person, Person dbPerson) {
        //This complex logic can be replaced with moving person training to its own repository with url /persontrainings.
        //But it will increase count of requests to server from UI
        Set<PersonTraining> personTrainings = person.getPersonTrainings();
        if (personTrainings == null || personTrainings.isEmpty()) {
            dbPerson.getPersonTrainings().clear();
            return;
        }
        Set<Long> personTrainingIdsToUpdate = personTrainings.stream().map(PersonTraining::getId).collect(Collectors.toSet());
        Set<PersonTraining> dbPersonTrainingsToUpdate = dbPerson.getPersonTrainings().stream()
                .filter(dbPersonTraining -> personTrainingIdsToUpdate.contains(dbPersonTraining.getId())).collect(Collectors.toSet());
        dbPerson.getPersonTrainings().clear();

        Function<PersonTraining, Boolean> isNewPersonTraining = personTraining -> personTraining.getId() == null;
        Map<Boolean, List<PersonTraining>> personTrainingsIsNew = personTrainings.stream()
                .collect(Collectors.groupingBy(isNewPersonTraining));

        addNewPersonTrainings(dbPerson, personTrainingsIsNew.get(TRUE));

        updateExistedPersonTrainings(dbPerson, dbPersonTrainingsToUpdate, personTrainingsIsNew.get(FALSE));

    }

    private void addNewPersonTrainings(Person dbPerson, List<PersonTraining> newPersonTrainings) {
        if (newPersonTrainings != null) {
            newPersonTrainings.forEach(newPersonTraining -> {
                newPersonTraining.setPerson(dbPerson);
                dbPerson.getPersonTrainings().add(newPersonTraining);
            });
        }
    }

    private void updateExistedPersonTrainings(Person dbPerson, Set<PersonTraining> dbPersonTrainingsToUpdate, List<PersonTraining> personTrainingsToUpdate) {
        if (personTrainingsToUpdate != null) {
            personTrainingsToUpdate.forEach(personTraining ->
                    dbPersonTrainingsToUpdate.stream()
                            .filter(dbPersonTraining -> {
                                Long id = dbPersonTraining.getId();
                                return personTraining.getId().equals(id);
                            })
                            .findFirst().ifPresent(dbPersonTraining -> {
                                dbPersonTraining.setStartDate(personTraining.getStartDate());
                                dbPersonTraining.setEndDate(personTraining.getEndDate());
                                dbPersonTraining.setProgress(personTraining.getProgress());
                                dbPersonTraining.setHided(personTraining.isHided());
                                dbPersonTraining.getCompletedTasks().clear();
                                dbPersonTraining.getCompletedTasks().addAll(personTraining.getCompletedTasks());
                                dbPerson.getPersonTrainings().add(dbPersonTraining);
                            }
                    )
            );
        }
    }
}
