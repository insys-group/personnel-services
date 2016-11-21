package com.insys.trapps;

import com.insys.trapps.model.*;
import com.insys.trapps.repository.ContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest(classes = TrappsApiApplication.class)
@Slf4j
public class ContractRepositoryTests {

    @Autowired
    private ContractRepository contractRepository;

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
        testContractList.forEach(item -> contractRepository.save(item));
    }

    private void deleteAll() {
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
        saveAll();

        Contract testContractNew = contractRepository.findByComments("Contract 1").get(0);
        testContractNew.setComments("contract 1 Updated");
        contractRepository.save(testContractNew);

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

}
