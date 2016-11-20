package com.insys.trapps;

import com.insys.trapps.model.*;
import com.insys.trapps.repository.EngagementRepository;
import com.insys.trapps.repository.RoleRepository;
import com.insys.trapps.repository.SkillRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Vladiomir Nalitkin
 *         Unit tests for Engagement engagementRepository. It uses H2 as in-memory database.
 *         These tests validates the save/update of the Engagement and related EngagementStep objects.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class EngagementRepositoryTests {

    @Autowired
    private EngagementRepository engagementRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SkillRepository skillRepository;

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
        skillRepository.save(testSkill);
        testRole = RoleBuilder.buildRole("Role 1")
                .name("Role 1")
                .addSkill(testSkill)
                .build();
        roleRepository.save(testRole);
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
        testEngagementList.forEach(item -> engagementRepository.save(item));
    }

    private void deleteAll() {
        testEngagementList.forEach(item -> engagementRepository.delete(item));
    }

    /*
     * Method to test Repository functionality for creating new.
     */
    @Test
    public void testSave() throws Exception {
        log.debug("Enter: testSave " + engagementRepository.getClass().toString());
        saveAll();

        engagementRepository.findAll().forEach(item -> assertNotNull(item.getId()));
        Set<Engagement> engagementsFromRepositorySet = new HashSet<>();
        engagementRepository.findAll().forEach(engagementsFromRepositorySet::add);
        testEngagementList.containsAll(engagementsFromRepositorySet);
        engagementsFromRepositorySet.forEach(item -> item
                .getEngagementOpenings()
                .containsAll(testEngagementList.get(testEngagementList.indexOf(item)).getEngagementOpenings())
        );
        engagementsFromRepositorySet.forEach(item -> log.debug("Engagement : " + item.toString()));

        deleteAll();
    }

    /*
     * Method to test Repository functionality for update.
     */
    @Test
    public void testUpdate() throws Exception {
        log.debug("Enter: testUpdate " + engagementRepository.getClass().toString());
        saveAll();

        Set<Engagement> engagementsFromRepositorySet = new HashSet<>();
        engagementRepository.findAll().forEach(engagementsFromRepositorySet::add);

        Engagement testEngagementNew = (Engagement) engagementsFromRepositorySet.toArray()[0];
        testEngagementNew.setComments("Engagement 1 Updated");
        engagementRepository.save(testEngagementNew);

        engagementsFromRepositorySet.clear();
        engagementRepository.findAll().forEach(engagementsFromRepositorySet::add);
        testEngagementList.containsAll(engagementsFromRepositorySet);
        engagementsFromRepositorySet.forEach(item -> item
                .getEngagementOpenings()
                .containsAll(testEngagementList.get(testEngagementList.indexOf(item)).getEngagementOpenings())
        );

        engagementsFromRepositorySet.forEach(item -> log.debug("Engagement : " + item.toString()));

        deleteAll();
    }


}