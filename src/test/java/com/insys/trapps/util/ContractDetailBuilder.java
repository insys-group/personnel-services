package com.insys.trapps.util;

import com.insys.trapps.model.ContractDetail;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * Created by vnalitkin on 11/18/2016.
 */
public class ContractDetailBuilder {
    private ContractDetail contractDetail = new ContractDetail();

    /*
    * This is a factory method for the Builder which builds the contractDetail object
     * @param comments Creates the contractDetail using the comments.
     * @return instance of contractDetailBuilder so that it can be chained.
     */
    public static ContractDetailBuilder buildContractDetail(String comments) {
        ContractDetailBuilder builder = new ContractDetailBuilder();
        builder.contractDetail.setComments(comments);
        return builder;
    }

    /*
     * This is a factory method for the Builder which builds the contractDetail object
     * @param contractDetail Creates the contractDetail using existing object. It is used to add more stuff to the contractDetail.
     * @return instance of contractDetailBuilder so that it can be chained.
     */
    public static ContractDetailBuilder buildContractDetail(ContractDetail contractDetail) {
        ContractDetailBuilder builder = new ContractDetailBuilder();
        builder.contractDetail = contractDetail;
        return builder;
    }

    public ContractDetailBuilder rate(BigDecimal _rate)
    {
        contractDetail.setRate(_rate);
        return this;
    }

    /*
    * This method returns the constructed Opportunity instance.
     * @return Opportunity Constructed Opportunity instance.
     */
    public ContractDetail build() {
        return contractDetail;
    }

}
