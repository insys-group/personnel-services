package com.insys.trapps.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;

/**
 * @author Muhammad Sabir
 * This is a helper to create Opportunity object with sensible defaults. It is used to test the Opportunity related scenarios.
 */

/**
 * @author msabir
 */
public class OpportunityBuilder {
    private Opportunity opportunity = new Opportunity();

    /*
     * This is a factory method for the Builder which builds the opportunity object
     * @param comments Creates the opportunity using the comments.
     * @return instance of OpportunityBuilder so that it can be chained.
     */
    public static OpportunityBuilder buildOpportunity(String comments) {
        OpportunityBuilder builder = new OpportunityBuilder();
        builder.opportunity.setComments(comments);
        return builder;
    }

    /*
     * This is a factory method for the Builder which builds the opportunity object
     * @param opportunity Creates the opportunity using existing object. It is used to add more stuff to the opportunity.
     * @return instance of OpportunityBuilder so that it can be chained.
     */
    public static OpportunityBuilder buildOpportunity(Opportunity opportunity) {
        OpportunityBuilder builder = new OpportunityBuilder();
        builder.opportunity = opportunity;
        return builder;
    }

    /*
     * This method is used to add step objects to the opportunity. It uses current time to be added to the step.
     * @param comments This param is used to add the comment to the OpportunityStep object
     * @return instance of OpportunityBuilder so that it can be chained.
     */
    public OpportunityBuilder addStep(String comments) {
        if (opportunity.getSteps() == null) {
            opportunity.setSteps(new HashSet<>());
        }
        opportunity.getSteps().add(new OpportunityStep(opportunity, comments, Timestamp.valueOf(LocalDateTime.now())));
        return this;
    }

    /*
     * This method returns the constructed Opportunity instance.
     * @return Opportunity Constructed Opportunity instance.
     */
    public Opportunity build() {
        return opportunity;
    }

}
