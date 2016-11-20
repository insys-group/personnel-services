package com.insys.trapps.util;

import com.insys.trapps.model.Contract;
import com.insys.trapps.model.Engagement;
import com.insys.trapps.model.EngagementOpening;
import com.insys.trapps.model.Role;

import java.math.BigDecimal;
import java.util.HashSet;

/**
 * Created by vnalitkin on 11/18/2016.
 */
public class EngagementOpeningBuilder {
    private EngagementOpening engagementOpening = new EngagementOpening();

    /*
    * This is a factory method for the Builder which builds the EngagementOpening object
     * @param comments Creates the EngagementOpening using the comments.
     * @return instance of EngagementOpeningBuilder so that it can be chained.
     */
    public static EngagementOpeningBuilder buildEngagementOpening(String comments) {
        EngagementOpeningBuilder builder = new EngagementOpeningBuilder();
        builder.engagementOpening.setComments(comments);
        return builder;
    }

    /*
     * This is a factory method for the Builder which builds the EngagementOpening object
     * @param EngagementOpening Creates the EngagementOpening using existing object. It is used to add more stuff to the EngagementOpening.
     * @return instance of EngagementOpeningBuilder so that it can be chained.
     */
    public static EngagementOpeningBuilder buildEngagementOpening(EngagementOpening engagementOpening) {
        EngagementOpeningBuilder builder = new EngagementOpeningBuilder();
        builder.engagementOpening = engagementOpening;
        return builder;
    }

    public EngagementOpeningBuilder rate(BigDecimal _rate)
    {
        engagementOpening.setRate(_rate);
        return this;
    }

    public EngagementOpeningBuilder addContract(Contract contract) {
        if (engagementOpening.getContracts() == null) {
            engagementOpening.setContracts(new HashSet<>());
        }
        engagementOpening.getContracts().add(contract);
        return this;
    }

    public EngagementOpeningBuilder engagement(Engagement engagement)
    {
        engagementOpening.setEngagement(engagement);
        return this;
    }

    public EngagementOpeningBuilder role(Role role)
    {
        engagementOpening.setRole(role);
        return this;
    }

    /*
    * This method returns the constructed Opportunity instance.
     * @return Opportunity Constructed Opportunity instance.
     */
    public EngagementOpening build() {
        return engagementOpening;
    }
}
