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
@Builder
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

    @Column(name = "COMMENTS")
    @Getter
    @Setter
    @NonNull
    protected String comments;

    @Getter
    @Setter
    @Singular
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ContractDetail> contractDetails;
}
