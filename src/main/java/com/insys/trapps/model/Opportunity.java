package com.insys.trapps.model;

import java.util.Set;

import javax.persistence.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Entity
@Table(name = "OPPORTUNITY")
@AllArgsConstructor
@NoArgsConstructor
public class Opportunity extends AbstractEntity{

    @Getter
    @Setter
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OpportunityStep> steps;

    @Getter
    @Setter
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Engagement> engagements;

    @ManyToOne
    @JoinColumn(name = "opportunity")
    private BusinessEntity businessEntity;

}