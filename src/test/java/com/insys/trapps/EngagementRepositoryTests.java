package com.insys.trapps;

import com.insys.trapps.model.*;
import com.insys.trapps.repository.EngagementRepository;
import com.insys.trapps.util.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Vladiomir Nalitkin
 *         Unit tests for Engagement repository. It uses H2 as in-memory database.
 *         These tests validates the save/update of the Engagement and related EngagementStep objects.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class EngagementRepositoryTests {

    @Autowired
    private EngagementRepository repository;

    private Engagement testEngagement;
    private EngagementOpening testEngagementOpening;
    private Contract testContract;
    private ContractDetail testContractDetail;
    private Role testRole;
    private Skill testSkill;
    private List<Engagement> testEngagementList = new ArrayList<>();

    /*
     * Initialize testContract1 (a subject) before every test method execution.
     */
    @Before
    public void beforeEachMethod() {
        testContractDetail = ContractDetailBuilder.buildContractDetail("Contract Detail 1")
                .rate(BigDecimal.TEN)
                .build();
        testContract = ContractBuilder.buildContract("Contract 1")
                .addDetail(testContractDetail)
                .build();
        testSkill = SkillBuilder.buildSkill("Skill 1")
                .name("Skill 1")
                .build();
        testRole = RoleBuilder.buildRole("Role 1")
                .name("Role 1")
                .addSkill(testSkill)
                .build();
        testEngagementOpening = EngagementOpeningBuilder.buildEngagementOpening("EngagementOpening 1")
                .rate(BigDecimal.TEN)
                .addContract(testContract)
                .role(testRole)
                .build();
        testEngagement = EngagementBuilder.buildEngagement("Engagement 1")
                .addEngagementOpening(testEngagementOpening)
                .build();
        testEngagementList.add(testEngagement);
    }

    private void saveAll() {
        testEngagementList.forEach(item -> repository.save(item));
    }

    private void deleteAll() {
        testEngagementList.forEach(item -> repository.delete(item));
    }

    /*
     * Method to test Repository functionality for creating new.
     */
    @Test
    public void testSaveContract() throws Exception {
        log.debug("Enter: testSaveContract");
        saveAll();

        repository.findAll().forEach(item -> assertNotNull(item.getId()));
        List<Engagement> engagements = repository.findByComments("Engagement 1");
        engagements.forEach(cont -> assertTrue(testEngagementList.indexOf(cont) > -1));

        Set<EngagementOpening> engagementOpenings = engagements.get(0).getEngagementOpenings();
        assertTrue(engagementOpenings.size() == testEngagement.getEngagementOpenings().size());

        log.debug("EngagementOpenings is " + engagements.get(0));
        deleteAll();
    }

    /*
     * Method to test Repository functionality for update.
     */
    @Test
    public void testUpdateOpportunity() throws Exception {
        log.debug("Enter: testUpdateContract");
        saveAll();

        repository.findAll().forEach(item -> assertNotNull(item.getId()));
        List<Engagement> engagements = repository.findByComments("Engagement 1");
        engagements.forEach(item -> assertTrue(testEngagementList.indexOf(item) > -1));

        Engagement testEngagement1 = EngagementBuilder.buildEngagement(engagements.get(0)).build();
        testEngagement.setComments("Engagement 1 Updated");
        repository.save(testEngagement);

        engagements = repository.findByComments("Engagement 1 Updated");
        Set<EngagementOpening> details = engagements.get(0).getEngagementOpenings();

        assertTrue(details.size() == testEngagement.getEngagementOpenings().size());

        log.debug("Engagements is " + engagements.get(0));

        deleteAll();
    }


}