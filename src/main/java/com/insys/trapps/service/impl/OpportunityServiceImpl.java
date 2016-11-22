package com.insys.trapps.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;
import com.insys.trapps.repository.OpportunityRepository;
import com.insys.trapps.service.OpportunityService;

/**
 * @author Muhammad Sabir
 *
 */
@Service
@Transactional
public class OpportunityServiceImpl implements OpportunityService {
	private static Logger logger = LoggerFactory.getLogger(OpportunityServiceImpl.class);
	private OpportunityRepository repository;
	
	@Autowired
	public OpportunityServiceImpl(OpportunityRepository repository) {
		this.repository=repository;
	}

	/* (non-Javadoc)
	 * @see com.insys.trapps.service.OpportunityService#searchOpportunityById()
	 */
	@Override
	public Opportunity searchOpportunityWithStepsAndContacts(Long id) {
		logger.debug("Enter: searchOpportunityById " + id);
		Opportunity opportunity=repository.findByIdWithSteps(id);
		logger.debug("Total Steps " + opportunity.getSteps().size());
		Opportunity opportunityContacts=repository.findByIdWithContacts(id);
		if(opportunityContacts.getContacts()!=null) {
			logger.debug("Total Contacts " + opportunity.getContacts().size());	
			opportunity.getContacts().addAll(opportunityContacts.getContacts());
		}
		return opportunity;
	}

	/* (non-Javadoc)
	 * @see com.insys.trapps.service.OpportunityService#listOpportunities()
	 */
	@Override
	public List<Opportunity> listOpportunities() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.insys.trapps.service.OpportunityService#saveOpportunity(com.insys.trapps.model.Opportunity)
	 */
	@Override
	public Opportunity saveOpportunity(Opportunity opportunity) {
		return repository.save(opportunity);
	}

	/* (non-Javadoc)
	 * @see com.insys.trapps.service.OpportunityService#saveStep(java.lang.Long, com.insys.trapps.model.OpportunityStep)
	 */
	@Override
	public OpportunityStep saveStep(Long opportunityId, OpportunityStep opportunityStep) {
		Opportunity opportunity=repository.findOne(opportunityId);
		opportunityStep.setOpportunity(opportunity);
		opportunity.getSteps().add(opportunityStep);
		opportunity=repository.save(opportunity);
		return opportunityStep;
	}
}
