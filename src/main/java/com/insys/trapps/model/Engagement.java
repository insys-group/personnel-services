package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ENGAGEMENT")
@AllArgsConstructor
@NoArgsConstructor
public class Engagement extends AbstractEntity {

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "OPPORTUNITY_ID")
    private Opportunity opportunity;

    @Getter
    @Setter
    @OneToMany(mappedBy = "engagement", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EngagementOpening> engagementOpenings;

}