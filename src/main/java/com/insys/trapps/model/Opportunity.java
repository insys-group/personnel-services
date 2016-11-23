package com.insys.trapps.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import lombok.ToString;


@Entity
@Table(name = "OPPORTUNITY")
@EqualsAndHashCode(of = {"comments"})
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    protected Long id;

    @Column(name = "COMMENTS")
    @Getter
    @Setter
    @NonNull
    protected String comments;

    
    @Setter
    @Singular
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OpportunityStep> steps;

    public Set<OpportunityStep> getSteps() {
    	return steps;
    }
    /*
    @Getter
    @Setter
    @Singular
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Engagement> engagements;
     
    @ManyToOne
    @JoinColumn(name = "BUSINESS_ENTITY_ID")
    private BusinessEntity businessEntity;
	*/
}