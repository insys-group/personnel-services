package com.insys.trapps.service.impl;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.PersonTraining;
import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.person.PersonDocument;
import com.insys.trapps.model.security.User;
import com.insys.trapps.respositories.PersonDocumentRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.UserRepository;
import com.insys.trapps.service.PersonService;
import com.insys.trapps.service.S3Service;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private Logger logger = Logger.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonDocumentRepository personDocumentRepository;

    @Autowired
    private S3Service s3Service;

    @Value("${aws.s3.buckets.person}")
    private String PERSON_BUCKET;

    public Person findPerson(Long id) {
        return personRepository.findOne(id);
    }

    public User findUser(String username) {
        return userRepository.getOne(username);
    }

    public PersonDocument saveDocument(Long id, String fileName, MultipartFile file) throws Exception {
        logger.debug("Enter: PersonServiceImpl.save(" + id + ", " + fileName + ", " + file.getName() + ")");

        Person person = personRepository.getOne(id);

        PersonDocument personDocument = new PersonDocument();
        personDocument.setFileName(fileName);
        personDocument.setFileSize(file.getSize());

        person.addDocument(personDocument);

        personRepository.saveAndFlush(person);

        personDocument = personDocumentRepository.findByFileName(fileName);

        String s3Key = "" + id + personDocument.getId();

        s3Service.upload(PERSON_BUCKET, file.getInputStream(), s3Key);

        personDocument.setS3key(s3Key);

        personDocument = personDocumentRepository.saveAndFlush(personDocument);

        return personDocument;
    }

    public PersonDocument getDocument(Long documentId) throws Exception {
        PersonDocument personDocument = personDocumentRepository.findOne(documentId);
        personDocument.setFile(s3Service.download(PERSON_BUCKET, personDocument.getS3key()));
        return personDocument;
    }

    public Boolean deleteDocument(Long personId, Long documentId) throws Exception {
        logger.debug("Enter: PersonServiceImpl.deleteDocument(" + documentId + ")");

        PersonDocument personDocument = personDocumentRepository.findOne(documentId);
        Boolean deleted = s3Service.remove(PERSON_BUCKET, personDocument.getS3key());

        Person person = personRepository.findOne(personId);
        Optional<PersonDocument> document = person.getPersonDocuments().stream().filter(doc -> doc.getId().equals(documentId)).findFirst();
        if (!document.isPresent()) {
            return null;
        }
        PersonDocument doc = document.get();
        person.getPersonDocuments().remove(doc);
        personRepository.saveAndFlush(person);

        return deleted;
    }

    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person save(Person person) {
        return personRepository.saveAndFlush(person);
    }

}