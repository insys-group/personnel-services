package com.insys.trapps.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Opportunity implements Serializable {
	private static final long serialVersionUID = -5560389393119101131L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NonNull
    protected String name;

    @Column(name = "COMMENTS")
    @Getter
    @Setter
    @NonNull
    protected String comments;

    @Getter
    @Setter
    @OneToMany(targetEntity = OpportunityStep.class, cascade = CascadeType.ALL, orphanRemoval=true)
    @OrderBy("ID ASC")
    private Set<OpportunityStep> opportunitySteps;

    @Getter
    @Setter
    @OneToMany(targetEntity = Engagement.class, cascade = CascadeType.ALL, orphanRemoval=true)
    @OrderBy("ID ASC")
    private Set<Engagement> engagements;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "BUSINESS_ID")
    private Business business;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "Person")
    private Person person;

}