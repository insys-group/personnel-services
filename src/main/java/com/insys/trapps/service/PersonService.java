/**
 * 
 */
package com.insys.trapps.service;

import org.springframework.web.multipart.MultipartFile;

import com.insys.trapps.model.Person;

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
	 * @param filename
	 * @param file
	 * @throws Exception 
	 */
	void save(Long id, String fileName, MultipartFile file) throws Exception;
	
}
