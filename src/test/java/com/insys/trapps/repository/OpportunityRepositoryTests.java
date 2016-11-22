package com.insys.trapps.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;
import com.insys.trapps.model.Person;
import com.insys.trapps.util.OpportunityBuilder;
import com.insys.trapps.util.PersonBuilder;

/**
 * @author Muhammad Sabir
 * Unit tests for Opportunity repository. It uses H2 as in-memory database.
 * These tests validates the save/update of the Opportunity and related OpportunityStep objects.  
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OpportunityRepositoryTests {
	private static final Logger logger = LoggerFactory.getLogger(OpportunityRepositoryTests.class); 
    @Autowired
    private OpportunityRepository repository;

    private Opportunity testOpportunity;
    
    /*
     * Initialize testOpportunity (a subject) before every test method execution.
     */
    @Before
    public void beforeEachMethod() {
    	testOpportunity=OpportunityBuilder.buildOpportunity("Comcast opportunity").addStep("Step 1").addStep("Step 2").build();
    }
    
    /*
     * Method to test OpportunityRepository functionality for creating new opportunities.
     */
    @Test
    public void testSaveOpportunity() throws Exception {
    	logger.debug("Enter: testSaveOpportunity");
        this.repository.saveAndFlush(testOpportunity);
        List<Opportunity> opportunities = this.repository.findByComments("Comcast opportunity");
        
        assertTrue(opportunities.size()==1);
        assertNotNull(opportunities.get(0).getId());
        
        //TODO Need to change this using hamcrest/mockito which can compare the whole list
        Collection<OpportunityStep> steps=opportunities.get(0).getSteps();
        assertTrue(steps.size()==testOpportunity.getSteps().size());
        steps.stream().forEach(step->{
        	assertTrue(testOpportunity.getSteps().contains(step));
        	assertNotNull(step.getStepTimestamp());
        });
        this.repository.delete(testOpportunity);
    }
    
    /*
     * Method to test OpportunityRepository functionality for updating existing opportunities.
     */
    @Test
    public void testUpdateOpportunity() throws Exception {
    	logger.debug("Enter: testUpdateOpportunity");
    	this.repository.saveAndFlush(testOpportunity);
        List<Opportunity> opportunities = this.repository.findByComments("Comcast opportunity");
        assertTrue(opportunities.size()==1);
        assertNotNull(opportunities.get(0).getId());
        
        this.testOpportunity=OpportunityBuilder.buildOpportunity(opportunities.get(0)).addStep("Step 3").build();
        this.testOpportunity.setComments("Comcast Opportunity Updated");
        this.repository.saveAndFlush(this.testOpportunity);
        
        opportunities = this.repository.findByComments("Comcast Opportunity Updated");
        Collection<OpportunityStep> steps=opportunities.get(0).getSteps();
        
        assertTrue(steps.size()==testOpportunity.getSteps().size());
        
        logger.debug("Opportunity is " + opportunities.get(0));
        
        //TODO Need to change this using hemcrest/mockito
        steps.stream().forEach(step->{
        	logger.debug("Step is " + step.toString());
        	assertTrue(testOpportunity.getSteps().contains(step));
        	assertNotNull(step.getStepTimestamp());
        	assertNotNull(step.getId());
        });
        this.repository.delete(testOpportunity);
    }
    
    @Test
    public void testSaveOpportunityWithContact() throws Exception {
    	logger.debug("Enter: testSaveOpportunityWithContact");
    	this.testOpportunity=OpportunityBuilder.buildOpportunity(testOpportunity).addContact(PersonBuilder.buildPerson("Muhammad", "Sabir").build()).build();
    	this.repository.saveAndFlush(testOpportunity);
    	
        List<Opportunity> opportunities = this.repository.findByComments("Comcast opportunity");
        assertTrue(opportunities.size()==1);
        assertNotNull(opportunities.get(0).getId());
        
        Collection<OpportunityStep> steps=opportunities.get(0).getSteps();
        Collection<Person> contacts=opportunities.get(0).getContacts();
        
        assertTrue(steps.size()==testOpportunity.getSteps().size());
        assertTrue(contacts.size()==testOpportunity.getContacts().size());
        
        this.repository.delete(testOpportunity);
    }
    
    @Test
    public void testOpportunityWithAddRemoveContact() throws Exception {
    	logger.debug("Enter: testUpdteOpportunityWithContact");
    	this.testOpportunity=OpportunityBuilder.buildOpportunity(testOpportunity).addContact(PersonBuilder.buildPerson("Muhammad", "Sabir").build()).build();
    	this.repository.saveAndFlush(testOpportunity);
    	
        List<Opportunity> opportunities = this.repository.findByComments("Comcast opportunity");
        assertTrue(opportunities.size()==1);
        assertNotNull(opportunities.get(0).getId());
        
        Collection<OpportunityStep> steps=opportunities.get(0).getSteps();
        Collection<Person> contacts=opportunities.get(0).getContacts();
        
        assertTrue(steps.size()==testOpportunity.getSteps().size());
        assertTrue(contacts.size()==testOpportunity.getContacts().size());
        
        Person p=testOpportunity.getContacts().stream().filter(person->person.getFirstName().equals("Muhammad")).findFirst().get();
        
        testOpportunity=OpportunityBuilder
        		.buildOpportunity(opportunities.get(0))
        		.addContact(PersonBuilder.buildPerson("Kris", "Krishna").build())
        		.removeContact(p)
        		.build();

        logger.debug("Saving Opportunity " + testOpportunity);
        this.repository.saveAndFlush(testOpportunity);
        
        opportunities = this.repository.findByComments("Comcast opportunity");
        steps=opportunities.get(0).getSteps();
        contacts=opportunities.get(0).getContacts();
        logger.debug("Step Count " + steps.size() + " Contact Count " + contacts.size());
        assertTrue(opportunities.size()==1);
        assertTrue(contacts.size()==testOpportunity.getContacts().size());

        this.repository.delete(testOpportunity);
    }

}
