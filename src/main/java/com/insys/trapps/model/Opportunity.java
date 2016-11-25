package com.insys.trapps.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Entity
@Table(name = "OPPORTUNITY")
@EqualsAndHashCode(exclude = {"steps", "engagements", "businessEntity"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Opportunity extends AbstractEntity {
    @Column(name = "COMMENTS")
    @Getter
    @Setter
    @NonNull
    protected String comments;

    @Getter
    @Setter
    @Singular
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OpportunityStep> steps;

    @Getter
    @Setter
    @Singular
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Engagement> engagements;

    @ManyToOne
    @JoinColumn(name = "BUSINESS_ENTITY_ID")
    private BusinessEntity businessEntity;

}