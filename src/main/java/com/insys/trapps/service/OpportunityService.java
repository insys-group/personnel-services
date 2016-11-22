package com.insys.trapps.service;

import java.util.List;

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;

/**
 * @author Muhammad Sabir
 *
 */
public interface OpportunityService {
	List<Opportunity> listOpportunities();
	/**
	 * @param opportunity
	 */
	Opportunity saveOpportunity(Opportunity opportunity);
	/**
	 * @param id
	 * @return
	 */
	Opportunity searchOpportunityWithStepsAndContacts(Long id);
	
	/**
	 * @param opportunityId
	 * @param opportunityStep
	 */
	OpportunityStep saveStep(Long opportunityId, OpportunityStep opportunityStep);
}
