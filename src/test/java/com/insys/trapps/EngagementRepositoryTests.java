package com.insys.trapps;

import com.insys.trapps.model.*;
import com.insys.trapps.repository.EngagementRepository;
import com.insys.trapps.repository.RoleRepository;
import com.insys.trapps.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertNotNull;

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

    private List<Engagement> testEngagementList = new ArrayList<>();

    /*
     * Initialize testContract1 (a subject) before every test method execution.
     */
    @Before
    public void beforeEachMethod() {
        Set<Skill> testSkillSet = new HashSet<>(
                Arrays.asList(
                        Skill.builder().name("Skill 1").build()
                        , Skill.builder().name("Skill 2").build()
                        , Skill.builder().name("Skill 3").build()
                )
        );
        testSkillSet.forEach(skillRepository::save);

        Role testRole = Role.builder()
                .name("Role 1")
                .skills(testSkillSet)
                .build();
        roleRepository.save(testRole);

        testEngagementList = Arrays.asList(
                Engagement.builder()
                        .comments("Engagement 1")
                        .engagementOpenings(new HashSet<>(
                                        Arrays.asList(
                                                EngagementOpening.builder()
                                                        .comments("EngagementOpening E1EO1")
                                                        .rate(BigDecimal.TEN)
                                                        .role(testRole)
                                                        .contracts(new HashSet<>(
                                                                        Arrays.asList(
                                                                                Contract.builder()
                                                                                        .comments("Contract E1EO1C1")
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E1EO1C1CD1")
                                                                                                .rate(BigDecimal.TEN)
                                                                                                .build())
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E1EO1C1CD2")
                                                                                                .rate(BigDecimal.ONE)
                                                                                                .build())
                                                                                        .build()
                                                                                , Contract.builder()
                                                                                        .comments("Contract E1EO1C2")
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E1EO1C2CD1")
                                                                                                .rate(BigDecimal.TEN)
                                                                                                .build())
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E1EO1C2CD2")
                                                                                                .rate(BigDecimal.ONE)
                                                                                                .build())
                                                                                        .build()
                                                                        )
                                                                )
                                                        )
                                                        .build()
                                                , EngagementOpening.builder()
                                                        .comments("EngagementOpening E1EO2")
                                                        .rate(BigDecimal.TEN)
                                                        .role(testRole)
                                                        .contracts(new HashSet<>(
                                                                        Arrays.asList(
                                                                                Contract.builder()
                                                                                        .comments("Contract E1EO2C1")
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E1EO2C1CD1")
                                                                                                .rate(BigDecimal.TEN)
                                                                                                .build())
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E1EO2C1CD2")
                                                                                                .rate(BigDecimal.ONE)
                                                                                                .build())
                                                                                        .build()
                                                                                , Contract.builder()
                                                                                        .comments("Contract E1EO2C2")
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E1EO2C2CD1")
                                                                                                .rate(BigDecimal.TEN)
                                                                                                .build())
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E1EO2C2CD2")
                                                                                                .rate(BigDecimal.ONE)
                                                                                                .build())
                                                                                        .build()
                                                                        )
                                                                )
                                                        )
                                                        .build()
                                        )
                                )
                        )
                        .build()
                , Engagement.builder()
                        .comments("Engagement 2")
                        .engagementOpenings(new HashSet<>(
                                        Arrays.asList(
                                                EngagementOpening.builder()
                                                        .comments("EngagementOpening E2EO1")
                                                        .rate(BigDecimal.TEN)
                                                        .role(testRole)
                                                        .contracts(new HashSet<>(
                                                                        Arrays.asList(
                                                                                Contract.builder()
                                                                                        .comments("Contract E2EO1C1")
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E2EO1C1CD1")
                                                                                                .rate(BigDecimal.TEN)
                                                                                                .build())
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E2EO1C1CD2")
                                                                                                .rate(BigDecimal.ONE)
                                                                                                .build())
                                                                                        .build()
                                                                                , Contract.builder()
                                                                                        .comments("Contract E2EO1C2")
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E2EO1C2CD1")
                                                                                                .rate(BigDecimal.TEN)
                                                                                                .build())
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E2EO1C2CD2")
                                                                                                .rate(BigDecimal.ONE)
                                                                                                .build())
                                                                                        .build()
                                                                        )
                                                                )
                                                        )
                                                        .build()
                                                , EngagementOpening.builder()
                                                        .comments("EngagementOpening E2EO2")
                                                        .rate(BigDecimal.TEN)
                                                        .role(testRole)
                                                        .contracts(new HashSet<>(
                                                                        Arrays.asList(
                                                                                Contract.builder()
                                                                                        .comments("Contract E2EO2C1")
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E2EO2C1CD1")
                                                                                                .rate(BigDecimal.TEN)
                                                                                                .build())
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E2EO2C1CD2")
                                                                                                .rate(BigDecimal.ONE)
                                                                                                .build())
                                                                                        .build()
                                                                                , Contract.builder()
                                                                                        .comments("Contract E2EO2C2")
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E2EO2C2CD1")
                                                                                                .rate(BigDecimal.TEN)
                                                                                                .build())
                                                                                        .contractDetail(ContractDetail.builder()
                                                                                                .comments("contractDetail E2EO2C2CD2")
                                                                                                .rate(BigDecimal.ONE)
                                                                                                .build())
                                                                                        .build()
                                                                        )
                                                                )
                                                        )
                                                        .build()
                                        )
                                )
                        )
                        .build()
        );
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