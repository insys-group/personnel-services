/**
 * 
 */
package com.insys.trapps.service;

import com.insys.trapps.model.person.PersonDocument;
import com.insys.trapps.model.security.User;
import org.springframework.web.multipart.MultipartFile;

import com.insys.trapps.model.person.Person;

/**
 * @author msabir
 *
 */
public interface PersonService {

    /**
     * @param id
     */
    Person findPerson(Long id);

    /**
     * @param username
     */
    User findUser(String username);

	Person findByEmail(String email);

	Person save(Person person);

	/**
	 * @param id
	 * @param fileName
	 * @param file
	 * @return
	 * @throws Exception
	 */
	PersonDocument saveDocument(Long id, String fileName, MultipartFile file) throws Exception;

	/**
	 * @param documentId
	 * @return
	 * @throws Exception
	 */
	PersonDocument getDocument(Long documentId) throws Exception;

	/**
	 * @param documentId
	 * @return
	 * @throws Exception
	 */
	Boolean deleteDocument(Long documentId) throws Exception;

}
