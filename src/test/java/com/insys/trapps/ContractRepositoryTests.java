package com.insys.trapps;

import com.insys.trapps.model.*;
import com.insys.trapps.repository.ContractRepository;
import com.insys.trapps.util.ContractBuilder;
import com.insys.trapps.util.ContractDetailBuilder;
import com.insys.trapps.util.OpportunityBuilder;
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
 * Created by vnalitkin on 11/18/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ContractRepositoryTests {

    @Autowired
    private ContractRepository repository;

    private Contract testContract1;
    private Contract testContract2;

    private ContractDetail testContractDetail1;
    private ContractDetail testContractDetail2;
    private ContractDetail testContractDetail3;

    private List<Contract> testContractList = new ArrayList<>();

    /*
     * Initialize testContract1 (a subject) before every test method execution.
     */
    @Before
    public void beforeEachMethod() {
        testContractDetail1 = ContractDetailBuilder.buildContractDetail("Contract Detail 1")
                .rate(BigDecimal.TEN)
                .build();
        testContractDetail2 = ContractDetailBuilder.buildContractDetail("Contract Detail 2")
                .rate(BigDecimal.ZERO)
                .build();
        testContractDetail3 = ContractDetailBuilder.buildContractDetail("Contract Detail 3")
                .rate(BigDecimal.ONE)
                .build();

        testContract1 = ContractBuilder.buildContract("Contract 1")
                .addDetail(testContractDetail1)
                .addDetail(testContractDetail2)
                .build();
        testContractList.add(testContract1);

        testContract2 = ContractBuilder.buildContract("Contract 2")
                .addDetail(testContractDetail1)
                .build();
        testContractList.add(testContract2);
    }

    private void saveAll(){
        testContractList.forEach(cont -> repository.save(cont));
    }

    private void deleteAll(){
        testContractList.forEach(cont -> repository.delete(cont));
    }

    /*
     * Method to test ContractyRepository functionality for creating new Contracts.
     */
    @Test
    public void testSaveContract() throws Exception {
        log.debug("Enter: testSaveContract");
        saveAll();
        repository.findAll().forEach(contract -> assertNotNull(contract.getId()));
        List<Contract> contracts = repository.findByComments("Contract 2");
        contracts.forEach(cont -> assertTrue(testContractList.indexOf(cont) > -1));

        Set<ContractDetail> contractDetails = contracts.get(0).getContractDetails();
        assertTrue(contractDetails.size() == testContract2.getContractDetails().size());

        log.debug("Opportunity is " + contracts.get(0));
        deleteAll();
    }

    @Test
    public void testUpdateOpportunity() throws Exception {
        log.debug("Enter: testUpdateContract");
        saveAll();

        List<Contract> contracts = this.repository.findByComments("Contract 1");
        assertTrue(contracts.size() == 1);
        assertNotNull(contracts.get(0).getId());

        Contract testContract = ContractBuilder.buildContract(contracts.get(0)).addDetail(testContractDetail3).build();
        testContract.setComments("Contract 1 Updated");
        repository.save(testContract);

        contracts = repository.findByComments("Contract 1 Updated");
        Set<ContractDetail> details = contracts.get(0).getContractDetails();

        assertTrue(details.size() == testContract.getContractDetails().size());

        log.debug("Contract is " + contracts.get(0));

        deleteAll();
    }

}
