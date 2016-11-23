package com.insys.trapps.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import com.insys.trapps.model.Business;
import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;

/**
 * Created by vnalitkin on 11/21/2016.
 */
public class OpportunityBuilder {
    private Opportunity opportunity;

    public static OpportunityBuilder buildOpportunity(String comment, Business business) {
        OpportunityBuilder builder = new OpportunityBuilder();
        builder.opportunity = Opportunity.builder()
                .comments(comment)
                //.businessEntity(businessEntity)
                .build();
        builder.opportunity
                .setSteps(new HashSet<>(
                                Arrays.asList(
                                        OpportunityStep.builder()
                                                .comments("OpportunityStep 1 " + builder.opportunity.getComments())
                                                .stepTimestamp(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                                                .opportunity(builder.opportunity)
                                                .build()
                                        , OpportunityStep.builder()
                                                .comments("OpportunityStep 2 " + builder.opportunity.getComments())
                                                .stepTimestamp(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                                                .opportunity(builder.opportunity)
                                                .build()
                                )
                        )
                );
        return builder;
    }

    public Opportunity build() {
        return opportunity;
    }


}
