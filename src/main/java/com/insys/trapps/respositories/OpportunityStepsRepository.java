package com.insys.trapps.respositories;

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by vladn on 25.11.2016.
 */
@RepositoryRestResource
public interface OpportunityStepsRepository extends JpaRepository<OpportunityStep, Long> {
    List<OpportunityStep> findByComments(String comments);
}
