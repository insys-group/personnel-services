package com.insys.trapps.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.*;

import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name = "OPPORTUNITY_STEP")
@EqualsAndHashCode(exclude = {"opportunity"}, callSuper = false)
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpportunityStep extends AbstractEntity {
    @Getter
    @Setter
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STEP_TIMESTAMP", nullable = false)
    private Date stepTimestamp;

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
