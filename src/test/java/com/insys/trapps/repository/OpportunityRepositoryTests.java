package com.insys.trapps.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import com.insys.trapps.TrappsApiApplication;
import com.insys.trapps.util.OpportunityBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;
import com.insys.trapps.respositories.OpportunityRepository;

/**
 * @author Muhammad Sabir
 *         Unit tests for Opportunity repository. It uses H2 as in-memory database.
 *         These tests validates the save/update of the Opportunity and related OpportunityStep objects.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrappsApiApplication.class)
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
                OpportunityBuilder.buildOpportunity("Opportunity 1", null).build()
                , OpportunityBuilder.buildOpportunity("Opportunity 2", null).build()
                , OpportunityBuilder.buildOpportunity("Opportunity 3", null).build()
        );
    }

    private void saveAll() {
        deleteAll();
        testOpportunityList.forEach(item -> opportunityRepository.save(item));
    }

    private void deleteAll() {
        opportunityRepository.deleteAll();
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
                .getOpportunitySteps()
                .containsAll(testOpportunityList.get(testOpportunityList.indexOf(item)).getOpportunitySteps())
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

        testOpportunityList.forEach(item -> item.setComments(item.getComments() + " upd"));
        saveAll();

        Set<Opportunity> opportunitysFromRepositorySet = new HashSet<>();
        opportunityRepository.findAll().forEach(opportunitysFromRepositorySet::add);
        testOpportunityList.containsAll(opportunitysFromRepositorySet);
        opportunitysFromRepositorySet.forEach(item -> {
                    assertTrue(testOpportunityList.indexOf(item) != -1);
                    item.getOpportunitySteps()
                            .containsAll(testOpportunityList.get(testOpportunityList.indexOf(item)).getOpportunitySteps());
                }
        );

        opportunitysFromRepositorySet.forEach(item -> log.debug("Opportunity : " + item.toString()));

        deleteAll();
    }

}
