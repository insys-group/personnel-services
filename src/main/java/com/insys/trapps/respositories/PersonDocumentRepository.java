package com.insys.trapps.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.PersonDocument;

/**
 * @author msabir
 *
 */
@RepositoryRestResource(path="/persondocuments", collectionResourceRel="persondocuments")
public interface PersonDocumentRepository extends JpaRepository<PersonDocument, Long>{

}
