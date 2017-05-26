package com.insys.trapps.respositories;

import com.insys.trapps.model.person.PersonDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDocumentRepository extends JpaRepository<PersonDocument, Long> {

}
