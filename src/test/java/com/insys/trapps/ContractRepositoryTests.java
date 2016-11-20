package com.insys.trapps;

import com.insys.trapps.model.*;
import com.insys.trapps.repository.ContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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

    private List<Contract> testContractList = new ArrayList<>();

    /*
     * Initialize testContract1 (a subject) before every test method execution.
     */
    @Before
    public void beforeEachMethod() {
        testContractList = Arrays.asList(
                Contract.builder()
                        .comments("Contract 1")
                        .contractDetails(new HashSet<>(
                                        Arrays.asList(ContractDetail.builder()
                                                        .comments("contractDetail C1CD1")
                                                        .rate(BigDecimal.TEN)
                                                        .startDate(LocalDate.now())
                                                        .endDate(LocalDate.now().plusDays(20))
                                                        .build()
                                                , ContractDetail.builder()
                                                        .comments("contractDetail C1CD2")
                                                        .rate(BigDecimal.ONE)
                                                        .startDate(LocalDate.now())
                                                        .endDate(LocalDate.now().plusDays(20))
                                                        .build()
                                        )
                                )
                        )
                        .build()
                , Contract.builder()
                        .comments("Contract 2")
                        .contractDetails(new HashSet<>(
                                        Arrays.asList(ContractDetail.builder()
                                                        .comments("contractDetail C2CD1")
                                                        .rate(BigDecimal.TEN)
                                                        .startDate(LocalDate.now())
                                                        .endDate(LocalDate.now().plusDays(20))
                                                        .build()
                                                , ContractDetail.builder()
                                                        .comments("contractDetail C2CD2")
                                                        .rate(BigDecimal.ONE)
                                                        .startDate(LocalDate.now())
                                                        .endDate(LocalDate.now().plusDays(20))
                                                        .build()
                                        )
                                )
                        )
                        .build()
        );
    }

    private void saveAll() {
        testContractList.forEach(item -> repository.save(item));
    }

    private void deleteAll() {
        testContractList.forEach(item -> repository.delete(item));
    }

    /*
     * Method to test ContractyRepository functionality for creating new Contracts.
     */
    @Test
    public void testSave() throws Exception {
        log.debug("Enter: testSave " + repository.getClass().toString());
        saveAll();

        repository.findAll().forEach(item -> assertNotNull(item.getId()));
        Set<Contract> contractsFromRepositorySet = new HashSet<>();
        repository.findAll().forEach(contractsFromRepositorySet::add);
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
        log.debug("Enter: testUpdate " + repository.getClass().toString());
        saveAll();

        Set<Contract> contractsFromRepositorySet = new HashSet<>();
        repository.findAll().forEach(contractsFromRepositorySet::add);

        Contract testContractNew = (Contract) contractsFromRepositorySet.toArray()[0];
        testContractNew.setComments("contract 1 Updated");
        repository.save(testContractNew);

        contractsFromRepositorySet.clear();
        repository.findAll().forEach(contractsFromRepositorySet::add);
        testContractList.containsAll(contractsFromRepositorySet);
        contractsFromRepositorySet.forEach(item -> item
                .getContractDetails()
                .containsAll(testContractList.get(testContractList.indexOf(item)).getContractDetails())
        );

        contractsFromRepositorySet.forEach(item -> log.debug("contract : " + item.toString()));

        deleteAll();
    }

}
