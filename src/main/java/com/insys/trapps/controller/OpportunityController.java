/**
 * 
 */
package com.insys.trapps.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;
import com.insys.trapps.service.OpportunityService;

/**
 * @author Muhammad Sabir
 *
 */
@RestController
public class OpportunityController {
	private static Logger logger=LoggerFactory.getLogger(OpportunityController.class);
	
	private OpportunityService service;
	
	@Autowired
	public OpportunityController(OpportunityService service) {
		this.service=service;
	}
	
	@RequestMapping(path="/opportunities", method=RequestMethod.GET)
	public ResponseEntity<List<Opportunity>> listOpportunities() {
		logger.debug("Enter: listOpportunities ");
		List<Opportunity> opportunities=service.listOpportunities();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(opportunities);
	}
	
	@RequestMapping(path="/opportunities", method=RequestMethod.POST)
	public ResponseEntity<Opportunity> createOpportunity(
			@RequestBody Opportunity opportunity
			) {
		logger.debug("Enter: createOpportunity " + opportunity);
		service.saveOpportunity(opportunity);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(opportunity);
	}
	
	@RequestMapping(path="/opportunities/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateOpportunity(
			@PathVariable(value="id", required=true) Long id, 
			@RequestBody Opportunity opportunity
			) {
		
		logger.debug("Enter: updateOpportunity id=" + id + ", Opportunity=" + opportunity);
		opportunity.setId(id);
		
		service.saveOpportunity(opportunity);
	}
	
	@RequestMapping(path="/opportunities/{opportunityId}/steps", method=RequestMethod.POST)
	public ResponseEntity<OpportunityStep> createStep(
			@PathVariable(value="opportunityId", required=true) Long opportunityId, 
			@RequestBody OpportunityStep opportunityStep
			) {
		
		logger.debug("Enter: createStep opportunityId=" + opportunityId + ", OpportunityStep=" + opportunityStep);
		opportunityStep=service.saveStep(opportunityId, opportunityStep);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(opportunityStep);
	}
	/*
	@RequestMapping(path="/opportunities/{opportunityId}/steps/{stepId}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateStep(
			@PathVariable(value="opportunityId", required=true) Long opportunityId, 
			@RequestBody OpportunityStep opportunityStep
			) {
		logger.debug("Enter: updateStep opportunityId=" + opportunityId + ", OpportunityStep=" + opportunityStep);
		service.saveStep(opportunityId, opportunityStep);
	}
	*/
}