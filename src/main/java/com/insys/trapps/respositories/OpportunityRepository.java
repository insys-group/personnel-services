package com.insys.trapps.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.insys.trapps.model.Opportunity;

@RepositoryRestResource
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
	List<Opportunity> findByComments(String comments);
}
