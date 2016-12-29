package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OPPORTUNITY_CONTACT")
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityContact {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Version
    @NonNull
    @Getter
    @Setter
    @Column(name = "VERSION")
    private Long version;

    @ManyToOne
    @JoinColumn(name = "opportunity_id")
    private Opportunity opportunity;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
