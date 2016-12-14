package com.insys.trapps.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;


@Entity
@Table(name = "OPPORTUNITY")
@EqualsAndHashCode(exclude = {"comments"}, callSuper = false)
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
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL)
    private Set<OpportunityStep> opportunitySteps;

    @Getter
    @Setter
    @Singular
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL)
    private Set<Engagement> engagements;

    @ManyToOne
    @JoinColumn(name = "BUSINESS_ID")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "Person")
    private Person person;

}