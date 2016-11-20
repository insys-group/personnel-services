package com.insys.trapps;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;
import com.insys.trapps.repository.OpportunityRepository;

/**
 * @author Muhammad Sabir
 *         Unit tests for Opportunity repository. It uses H2 as in-memory database.
 *         These tests validates the save/update of the Opportunity and related OpportunityStep objects.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class OpportunityRepositoryTests {

    @Autowired
    private OpportunityRepository opportunityRepository;

    private List<Opportunity> testOpportunityList = new ArrayList<>();

    /*
     * Initialize testOpportunity (a subject) before every test method execution.
     */
    @Before
    public void beforeEachMethod() {
        testOpportunityList = Arrays.asList(
                Opportunity.builder()
                        .comments("Comcast opportunity 1")
                        .steps(new HashSet<>(
                                Arrays.asList(
                                        OpportunityStep.builder()
                                                .comments("OpportunityStep O1OS1")
                                                .build()
                                        , OpportunityStep.builder()
                                                .comments("OpportunityStep O1OS2")
                                                .build()
                                )))
                        .build()
                , Opportunity.builder()
                        .comments("Comcast opportunity 2")
                        .steps(new HashSet<>(
                                Arrays.asList(
                                        OpportunityStep.builder()
                                                .comments("OpportunityStep O2OS1")
                                                .stepTimestamp(LocalDate.now())
                                                .build()
                                        , OpportunityStep.builder()
                                                .comments("OpportunityStep O2OS2")
                                                .build()
                                )))
                        .build()
        );
    }


    private void saveAll() {
        testOpportunityList.forEach(item -> opportunityRepository.save(item));
    }

    private void deleteAll() {
        testOpportunityList.forEach(item -> opportunityRepository.delete(item));
    }

    /*
     * Method to test Repository functionality for creating new.
     */
    @Test
    public void testSave() throws Exception {
        log.debug("Enter: testSave " + opportunityRepository.getClass().toString());
        saveAll();

        opportunityRepository.findAll().forEach(item -> assertNotNull(item.getId()));
        Set<Opportunity> opportunitysFromRepositorySet = new HashSet<>();
        opportunityRepository.findAll().forEach(opportunitysFromRepositorySet::add);
        testOpportunityList.containsAll(opportunitysFromRepositorySet);
        opportunitysFromRepositorySet.forEach(item -> item
                .getSteps()
                .containsAll(testOpportunityList.get(testOpportunityList.indexOf(item)).getSteps())
        );
        opportunitysFromRepositorySet.forEach(item -> log.debug("Opportunity : " + item.toString()));

        deleteAll();
    }

    /*
     * Method to test Repository functionality for update.
     */
    @Test
    public void testUpdate() throws Exception {
        log.debug("Enter: testUpdate " + opportunityRepository.getClass().toString());
        saveAll();

        Set<Opportunity> opportunitysFromRepositorySet = new HashSet<>();
        opportunityRepository.findAll().forEach(opportunitysFromRepositorySet::add);

        Opportunity testOpportunityNew = (Opportunity) opportunitysFromRepositorySet.toArray()[0];
        testOpportunityNew.setComments("Opportunity 1 Updated");
        opportunityRepository.save(testOpportunityNew);

        opportunitysFromRepositorySet.clear();
        opportunityRepository.findAll().forEach(opportunitysFromRepositorySet::add);
        testOpportunityList.containsAll(opportunitysFromRepositorySet);
        opportunitysFromRepositorySet.forEach(item -> item
                .getSteps()
                .containsAll(testOpportunityList.get(testOpportunityList.indexOf(item)).getSteps())
        );

        opportunitysFromRepositorySet.forEach(item -> log.debug("Opportunity : " + item.toString()));

        deleteAll();
    }

}
