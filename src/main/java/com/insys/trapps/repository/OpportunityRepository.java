package com.insys.trapps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insys.trapps.model.Opportunity;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
	public List<Opportunity> findByComments(String comments);
	
	@Query("select o from Opportunity o left join fetch o.steps s where o.id = (:id)")
    public Opportunity findByIdWithSteps(@Param("id") Long id);
	
	@Query("select o from Opportunity o left join fetch o.contacts s where o.id = (:id)")
    public Opportunity findByIdWithContacts(@Param("id") Long id);
}
