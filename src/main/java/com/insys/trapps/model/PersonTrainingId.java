package com.insys.trapps.model;

import java.io.Serializable;

/**
 * Created by kkrishnainsys.com on 2/16/17.
 */
public class PersonTrainingId implements Serializable {

    private long trainingId;

    private long personId;

    public int hashCode() {
        return (int) (trainingId + personId);
    }

    public boolean equals(Object object) {
        if (object instanceof PersonTrainingId) {
            PersonTrainingId otherId = (PersonTrainingId) object;
            return (otherId.trainingId == this.trainingId) && (otherId.personId == this.personId);
        }
        return false;
    }

}
