package com.insys.trapps.respositories;

import com.insys.trapps.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
//@PreAuthorize("hasAuthority('ADMIN')")
public interface PersonRepository extends JpaRepository<Person, Long> {

    public Person findByEmail(String email);

}
