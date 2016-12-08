package com.insys.trapps.respositories;

import com.insys.trapps.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by vnalitkin on 12/7/16.
 */

@RepositoryRestResource
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
}
