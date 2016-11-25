package com.insys.trapps;

import com.insys.trapps.util.ContractBuilder;
import com.insys.trapps.model.*;
import com.insys.trapps.respositories.ContractDetailRepository;
import com.insys.trapps.respositories.ContractRepository;
import lombok.extern.slf4j.Slf4j;
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
 * Created by vnalitkin on 11/18/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrappsApiApplication.class)
@Slf4j
public class ContractRepositoryTests {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractDetailRepository contractDetailRepository;


    private List<Contract> testContractList = new ArrayList<>();

    /*
     * Initialize testContract1 (a subject) before every test method execution.
     */
    @Before
    public void beforeEachMethod() {
        testContractList = Arrays.asList(
                ContractBuilder.buildContract("Contract 1", null, null).build()
                , ContractBuilder.buildContract("Contract 2", null, null).build()
        );
    }

    private void saveAll() {
        deleteAll();
        testContractList.forEach(item -> contractRepository.save(item));
    }

    private void deleteAll() {
        contractDetailRepository.deleteAll();
        contractRepository.deleteAll();
    }

    /*
     * Method to test ContractyRepository functionality for creating new Contracts.
     */
    @Test
    public void testSave() throws Exception {
        log.debug("Enter: testSave " + contractRepository.getClass().toString());
        saveAll();

        contractRepository.findAll().forEach(item -> assertNotNull(item.getId()));
        Set<Contract> contractsFromRepositorySet = new HashSet<>();
        contractRepository.findAll().forEach(contractsFromRepositorySet::add);
        testContractList.containsAll(contractsFromRepositorySet);
        contractsFromRepositorySet.forEach(item -> item
                .getContractDetails()
                .containsAll(testContractList.get(testContractList.indexOf(item)).getContractDetails())
        );
        contractsFromRepositorySet.forEach(item -> log.debug("contract : " + item.toString()));

        deleteAll();
    }

    /*
    * Method to test Repository functionality for update.
    */
    @Test
    public void testUpdate() throws Exception {
        log.debug("Enter: testUpdate " + contractRepository.getClass().toString());

        testContractList.forEach(item-> item.setComments(item.getComments() + " upd"));
        saveAll();

        Set<Contract> contractsFromRepositorySet = new HashSet<>();
        contractRepository.findAll().forEach(contractsFromRepositorySet::add);
        testContractList.containsAll(contractsFromRepositorySet);
        contractsFromRepositorySet.forEach(item -> {
                    assertTrue(testContractList.indexOf(item) != -1);
                    item.getContractDetails()
                        .containsAll(testContractList.get(testContractList.indexOf(item)).getContractDetails());
                }
        );

        contractsFromRepositorySet.forEach(item -> log.debug("contract : " + item.toString()));

        deleteAll();
    }
}
