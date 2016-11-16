package com.insys.trapps.util;

/**
 * @author Vladimir Nalitkin
 * This is a helper to create Engagement object with sensible defaults. It is used to test the Engagement related scenarios.
 */

import com.insys.trapps.model.Engagement;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * @author vnalitkin
 */
public class EngagementBuilder {

/*
    */
/*
     * This method is used to add step objects to the Engagement. It uses current time to be added to the step.
     * @param comments This param is used to add the comment to the EngagementStep object
     * @return instance of EngagementBuilder so that it can be chained.
     *//*

    public EngagementBuilder addStep(String comments) {
        if (Engagement.getSteps() == null) {
            Engagement.setSteps(new HashSet<>());
        }
        Engagement.getSteps().add(new EngagementStep(Engagement, comments, Timestamp.valueOf(LocalDateTime.now())));
        return this;
    }
*/

}
