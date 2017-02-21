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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author msabir
 *
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

        dbPerson.getPersonTrainings().clear();
        if (person.getPersonTrainings() != null) {
            person.getPersonTrainings().forEach(personTraining -> {
                personTraining.setPerson(dbPerson);
                dbPerson.getPersonTrainings().add(personTraining);
            });
        }

        logger.debug("Address is " + (dbPerson.getAddress() == null ? "address is null" : dbPerson.getAddress().toString()));
        dbPerson.setBusiness(person.getBusiness());
        dbPerson.setFirstName(person.getFirstName());
        dbPerson.setLastName(person.getLastName());
        dbPerson.setPhone(person.getPhone());
        dbPerson.setEmail(person.getEmail());
        dbPerson.setTitle(person.getTitle());
        dbPerson.setPersonType(person.getPersonType());

        Person savedPerson = repository.saveAndFlush(dbPerson);

        updateCompletedTasks(person, savedPerson);

        repository.saveAndFlush(savedPerson);

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

    @Override
    public Person savePerson(Person person) {
        if (person.getPersonTrainings() != null) {
            person.getPersonTrainings().forEach(personTraining -> {
                personTraining.setPerson(person);
                Long trainingId = personTraining.getTraining().getId();
                Training dbTraining = trainingRepository.findOne(trainingId);
                personTraining.setTraining(dbTraining);
            });
        }
        return repository.saveAndFlush(person);
    }

    private void updateCompletedTasks(Person person, Person savedPerson) {
        Set<TrainingTask> allPersonTasks = savedPerson.getPersonTrainings().stream()
                .flatMap(personTraining -> personTraining.getTraining().getTasks().stream())
                .collect(Collectors.toSet());

        for (PersonTraining dbPersonTraining : savedPerson.getPersonTrainings()) {
            if (person.getPersonTrainings() != null) {
                person.getPersonTrainings().stream()
                        .filter(dbPersonTraining::equals)
                        .forEach(personTraining -> {
                            Set<TrainingTask> dbTrainingTasks = allPersonTasks.stream()
                                    .filter(personTask -> {
                                        Set<TrainingTask> completedTasks = personTraining.getCompletedTasks();
                                        return completedTasks != null && completedTasks.stream().map(TrainingTask::getId).collect(Collectors.toList()).contains(personTask.getId());
                                    })
                                    .collect(Collectors.toSet());
                            dbPersonTraining.getCompletedTasks().addAll(dbTrainingTasks);
                        });
            }
        }
    }
}
