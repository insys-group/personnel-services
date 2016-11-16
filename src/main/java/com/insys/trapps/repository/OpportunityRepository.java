package com.insys.trapps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insys.trapps.model.Opportunity;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
	public List<Opportunity> findByComments(String comments);
}
