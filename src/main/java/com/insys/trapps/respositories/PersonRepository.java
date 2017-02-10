package com.insys.trapps.respositories;

import com.insys.trapps.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAll();
}
