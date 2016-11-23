package com.insys.trapps.model;

import lombok.*;

import java.util.Date;
import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Entity
@Table(name = "OPPORTUNITY_STEP")
@EqualsAndHashCode(of = {"comments"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"opportunity"})
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class OpportunityStep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    protected Long id;

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

    @Setter
    @ManyToOne
    @JoinColumn(name = "OPPORTUNITY_ID")
    @RestResource
    private Opportunity opportunity;

    /*
    @Setter
    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person contact;
     */
    @JsonIgnore
    public Opportunity getOpportunity() {
    	return opportunity;
    }
    /*
    @JsonIgnore
    public Person getContact() {
    	return contact;
    }
    */
}
