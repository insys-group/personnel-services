package com.insys.trapps.util;

import com.insys.trapps.model.Contract;
import com.insys.trapps.model.ContractDetail;
import com.insys.trapps.model.Opportunity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Created by vnalitkin on 11/18/2016.
 */
public class ContractBuilder {
    private Contract contract = new Contract();

    /*
    * This is a factory method for the Builder which builds the contract object
     * @param comments Creates the contract using the comments.
     * @return instance of contractBuilder so that it can be chained.
     */
    public static ContractBuilder buildContract(String comments) {
        ContractBuilder builder = new ContractBuilder();
        builder.contract.setComments(comments);
        return builder;
    }

    /*
     * This is a factory method for the Builder which builds the contract object
     * @param contract Creates the contract using existing object. It is used to add more stuff to the contract.
     * @return instance of contractBuilder so that it can be chained.
     */
    public static ContractBuilder buildContract(Contract contract) {
        ContractBuilder builder = new ContractBuilder();
        builder.contract = contract;
        return builder;
    }

    public ContractBuilder addDetail(ContractDetail contractDetail) {
        if (contract.getContractDetails() == null) {
            contract.setContractDetails(new HashSet<>());
        }
        contract.getContractDetails().add(contractDetail);
        return this;
    }

    /*
    * This method returns the constructed Opportunity instance.
     * @return Opportunity Constructed Opportunity instance.
     */
    public Contract build() {
        return contract;
    }

}
