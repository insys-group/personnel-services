package com.insys.trapps.util;

import com.insys.trapps.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by vnalitkin on 11/21/2016.
 */
public class ContractBuilder {

    private Contract contract;

    public static ContractBuilder buildContract(String comment, EngagementOpening engagementOpening, Person person) {
        ContractBuilder builder = new ContractBuilder();
        builder.contract = Contract.builder()
                .comments(comment)
                .engagementOpening(engagementOpening)
                .person(person)
                .build();
        builder.contract
                .setContractDetails(new HashSet<>(
                                Arrays.asList(ContractDetail.builder()
                                                .comments("contractDetail CD1 " + builder.contract.getComments())
                                                .rate(BigDecimal.TEN)
                                                .startDate(LocalDate.now())
                                                .endDate(LocalDate.now().plusDays(20))
                                                .contract(builder.contract)
                                                .build()
                                        , ContractDetail.builder()
                                                .comments("contractDetail CD2 " + builder.contract.getComments())
                                                .rate(BigDecimal.ONE)
                                                .startDate(LocalDate.now())
                                                .endDate(LocalDate.now().plusDays(20))
                                                .contract(builder.contract)
                                                .build()
                                )
                        )
                );
        return builder;
    }

    public Contract build() {
        return contract;
    }

}
