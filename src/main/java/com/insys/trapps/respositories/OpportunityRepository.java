package com.insys.trapps.respositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.insys.trapps.model.Opportunity;

@RepositoryRestResource
public interface OpportunityRepository extends CrudRepository<Opportunity, Long> {
	List<Opportunity> findByComments(String comments);
}
