/**
 * 
 */
package com.insys.trapps.service;

import org.springframework.web.multipart.MultipartFile;

import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonDocument;

/**
 * @author msabir
 *
 */
public interface PersonService {

	/**
	 * @param id
	 * @param person
	 */
	void updatePerson(Long id, Person person);

	/**
	 * @param id
	 * @param documentId
	 * @param file
	 * @return
	 * @throws Exception
	 */
	PersonDocument save(Long id, String fileName, MultipartFile file) throws Exception;

	/**
	 * @param id
	 * @param documentId
	 * @return
	 * @throws Exception
	 */
	PersonDocument getDocument(Long id, Long documentId) throws Exception;

	/**
	 * @param id
	 * @param documentId
	 * @return
	 * @throws Exception
	 */
	PersonDocument deleteDocument(Long id, Long documentId) throws Exception;
}
