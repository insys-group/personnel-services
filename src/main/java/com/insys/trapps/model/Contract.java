package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "CONTRACT")
@AllArgsConstructor
@NoArgsConstructor
public class Contract extends AbstractEntity {

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ENGAGEMENT_OPENING_ID")
    private EngagementOpening engagementOpening;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Getter
    @Setter
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private Set<ContractDetail> contractDetails;
}
