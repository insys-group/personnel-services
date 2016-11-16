package com.insys.trapps;

import com.insys.trapps.model.Engagement;
import com.insys.trapps.repository.EngagementRepository;
import com.insys.trapps.util.EngagementBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Vladiomir Nalitkin
 * Unit tests for Engagement repository. It uses H2 as in-memory database.
 * These tests validates the save/update of the Engagement and related EngagementStep objects.  
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class EngagementRepositoryTests {

    @Autowired
    private EngagementRepository repository;

    private Engagement testEngagement;

/*
    */
/*
     * Initialize testEngagement (a subject) before every test method execution.
     *//*

    @Before
    public void beforeEachMethod() {
        testEngagement= EngagementBuilder.buildEngagement("Comcast Engagement").addStep("Step 1").addStep("Step 2").build();
    }

    */
/*
     * Method to test EngagementRepository functionality for creating new opportunities.
     *//*

    @Test
    public void testSaveEngagement() throws Exception {
        log.debug("Enter: testSaveEngagement");
        this.repository.save(testEngagement);
        List<Engagement> opportunities = this.repository.findByComments("Comcast Engagement");

        assertTrue(opportunities.size()==1);
        assertNotNull(opportunities.get(0).getId());

        //TODO Need to change this using hamcrest/mockito which can compare the whole list
        Set<EngagementStep> steps=opportunities.get(0).getSteps();
        assertTrue(steps.size()==testEngagement.getSteps().size());
        steps.stream().forEach(step->{
            assertTrue(testEngagement.getSteps().contains(step));
            assertNotNull(step.getStepTimestamp());
        });
        this.repository.delete(testEngagement);
    }
    
     */
/* Method to test EngagementRepository functionality for updating existing opportunities.*//*



    @Test
    public void testUpdateEngagement() throws Exception {
        log.debug("Enter: testUpdateEngagement");
        this.repository.save(testEngagement);
        List<Engagement> opportunities = this.repository.findByComments("Comcast Engagement");
        assertTrue(opportunities.size()==1);
        assertNotNull(opportunities.get(0).getId());

        this.testEngagement=EngagementBuilder.buildEngagement(opportunities.get(0)).addStep("Step 3").build();
        this.testEngagement.setComments("Comcast Engagement Updated");
        this.repository.save(this.testEngagement);

        opportunities = this.repository.findByComments("Comcast Engagement Updated");
        Set<EngagementStep> steps=opportunities.get(0).getSteps();

        assertTrue(steps.size()==testEngagement.getSteps().size());

        log.debug("Engagement is " + opportunities.get(0));

        //TODO Need to change this using hemcrest/mockito
        steps.stream().forEach(step->{
            log.debug("Step is " + step.toString());
            assertTrue(testEngagement.getSteps().contains(step));
            assertNotNull(step.getStepTimestamp());
        });
        this.repository.delete(testEngagement);
    }
*/

}