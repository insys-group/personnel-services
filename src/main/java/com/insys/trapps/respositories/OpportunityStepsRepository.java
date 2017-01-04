package com.insys.trapps.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.OpportunityStep;

/**
 * Created by vladn on 25.11.2016.
 */
@RepositoryRestResource
public interface OpportunityStepsRepository extends JpaRepository<OpportunityStep, Long> {
    List<OpportunityStep> findByComments(String comments);
}
