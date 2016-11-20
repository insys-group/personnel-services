package com.insys.trapps.util;

/**
 * @author Vladimir Nalitkin
 * This is a helper to create Engagement object with sensible defaults. It is used to test the Engagement related scenarios.
 */

import com.insys.trapps.model.Engagement;
import com.insys.trapps.model.EngagementOpening;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * @author vnalitkin
 */
public class EngagementBuilder {
    private Engagement engagement = new Engagement();

    /*
    * This is a factory method for the Builder which builds the Engagement object
     * @param comments Creates the Engagement using the comments.
     * @return instance of EngagementBuilder so that it can be chained.
     */
    public static EngagementBuilder buildEngagement(String comments) {
        EngagementBuilder builder = new EngagementBuilder();
        builder.engagement.setComments(comments);
        return builder;
    }

    /*
     * This is a factory method for the Builder which builds the Engagement object
     * @param Engagement Creates the Engagement using existing object. It is used to add more stuff to the Engagement.
     * @return instance of EngagementBuilder so that it can be chained.
     */
    public static EngagementBuilder buildEngagement(Engagement engagement) {
        EngagementBuilder builder = new EngagementBuilder();
        builder.engagement = engagement;
        return builder;
    }

    public EngagementBuilder addEngagementOpening(EngagementOpening engagementOpening) {
        if (engagement.getEngagementOpenings() == null) {
            engagement.setEngagementOpenings(new HashSet<>());
        }
        engagement.getEngagementOpenings().add(engagementOpening);
        return this;
    }

    /*
    * This method returns the constructed Opportunity instance.
     * @return Opportunity Constructed Opportunity instance.
     */
    public Engagement build() {
        return engagement;
    }
}
