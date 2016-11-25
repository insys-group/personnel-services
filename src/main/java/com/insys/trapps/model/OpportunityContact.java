package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OPPORTUNITY_CONTACT")
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityContact extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "opportunity_id")
    private Opportunity opportunity;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
