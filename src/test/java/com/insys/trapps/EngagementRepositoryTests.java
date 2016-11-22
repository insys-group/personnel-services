package com.insys.trapps;

import com.insys.trapps.respositories.ContractRepository;
import com.insys.trapps.util.ContractBuilder;
import com.insys.trapps.util.EngagementBuilder;
import com.insys.trapps.model.*;
import com.insys.trapps.respositories.EngagementRepository;
import com.insys.trapps.respositories.RoleRepository;
import com.insys.trapps.respositories.SkillRepository;
import com.insys.trapps.util.RoleBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Vladiomir Nalitkin
 *         Unit tests for Engagement engagementRepository. It uses H2 as in-memory database.
 *         These tests validates the save/update of the Engagement and related EngagementStep objects.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrappsApiApplication.class)
@Slf4j
public class EngagementRepositoryTests {

    @Autowired
    private EngagementRepository engagementRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ContractRepository contractRepository;


    private List<Engagement> testEngagementList = new ArrayList<>();

    /*
     * Initialize testContract1 (a subject) before every test method execution.
     */
    @Before
    public void beforeEachMethod() {
        Role role = RoleBuilder.buildRole("Role 1").build();
        roleRepository.save(role);

        testEngagementList = Arrays.asList(
                EngagementBuilder.buildEngagement("Engagement 1", null, role, null).build()
                , EngagementBuilder.buildEngagement("Engagement 1", null, role, null).build()
        );
    }

    private void saveAll() {
        deleteAll();
        testEngagementList.forEach(item -> engagementRepository.save(item));
    }

    private void deleteAll() {
        engagementRepository.deleteAll();
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
        engagementsFromRepositorySet.forEach(item -> {
                    assertTrue(testEngagementList.indexOf(item) != -1);
                    item.getEngagementOpenings()
                        .containsAll(testEngagementList.get(testEngagementList.indexOf(item)).getEngagementOpenings());
                }
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

        testEngagementList.forEach(item-> item.setComments(item.getComments() + " upd"));
        saveAll();

        Set<Engagement> engagementsFromRepositorySet = new HashSet<>();
        engagementRepository.findAll().forEach(engagementsFromRepositorySet::add);
        testEngagementList.containsAll(engagementsFromRepositorySet);
        engagementsFromRepositorySet.forEach(item -> {
                    assertTrue(testEngagementList.indexOf(item) != -1);
                    item.getEngagementOpenings()
                            .containsAll(testEngagementList.get(testEngagementList.indexOf(item)).getEngagementOpenings());
                }
        );

        engagementsFromRepositorySet.forEach(item -> log.debug("Engagement : " + item.toString()));

        deleteAll();
    }

    @After
    public void afterTests() {
        roleRepository.deleteAll();
        contractRepository.deleteAll();
    }


}