package com.insys.trapps.util;

import com.insys.trapps.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by vnalitkin on 11/21/2016.
 */
public class EngagementBuilder {
    private Engagement engagement;

    public static EngagementBuilder buildEngagement(String comment, Opportunity opportunity, Role role , Location location) {
        EngagementBuilder builder = new EngagementBuilder();
        builder.engagement = Engagement.builder()
                .comments("Engagement 1")
                .opportunity(opportunity)
                .build();

        builder.engagement
                .setEngagementOpenings(new HashSet<>(
                                Arrays.asList(
                                        EngagementOpening.builder()
                                                .comments("EngagementOpening 1 " + builder.engagement.getComments())
                                                .engagement(builder.engagement)
                                                .rate(BigDecimal.TEN)
                                                .role(role)
                                                .location(location)
                                                .build()
                                        , EngagementOpening.builder()
                                                .comments("EngagementOpening 1 " + builder.engagement.getComments())
                                                .engagement(builder.engagement)
                                                .rate(BigDecimal.TEN)
                                                .role(role)
                                                .location(location)
                                                .build()

                                )
                        )
                );

        return builder;
    }

    public Engagement build() {
        return engagement;
    }

}