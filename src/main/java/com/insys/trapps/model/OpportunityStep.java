package com.insys.trapps.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "OPPORTUNITY_STEP")
@EqualsAndHashCode(exclude = {"comments", "stepTimestamp"}, callSuper = false)
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpportunityStep extends AbstractEntity {
    @Getter
    @Setter
    @NonNull
    @Column(name = "STEP_TIMESTAMP" , nullable = false)
    private Timestamp stepTimestamp;

    @Column(name = "COMMENTS")
    @Getter
    @Setter
    @NonNull
    protected String comments;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "OPPORTUNITY_ID", nullable = false)
    private Opportunity opportunity;
}
