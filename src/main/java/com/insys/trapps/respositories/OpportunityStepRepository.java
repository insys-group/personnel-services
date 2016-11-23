/**
 * 
 */
package com.insys.trapps.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.insys.trapps.model.OpportunityStep;

/**
 * @author Muhammad Sabir
 *
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "steps", path = "steps")
public interface OpportunityStepRepository extends JpaRepository<OpportunityStep, Long> {
}
