/**
 * 
 */
package com.insys.trapps.service;

import com.insys.trapps.model.security.User;
import org.springframework.web.multipart.MultipartFile;

import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.person.PersonDocument;

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

	/**
	 * @param id
	 * @param fileName
	 * @param file
	 * @return
	 * @throws Exception
	 */
//	PersonDocument save(Long id, String fileName, MultipartFile file) throws Exception;

	/**
	 * @param id
	 * @param documentId
	 * @return
	 * @throws Exception
	 */
//	PersonDocument getDocument(Long id, Long documentId) throws Exception;

	/**
	 * @param id
	 * @param documentId
	 * @return
	 * @throws Exception
	 */
//	PersonDocument deleteDocument(Long id, Long documentId) throws Exception;

	Person findByEmail(String email);

	Person save(Person person);

}
