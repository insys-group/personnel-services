package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "LOCATION")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location extends AbstractEntity {
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "BUSINESS_ENTITY_ID")
    private BusinessEntity businessEntity;

    @Getter
    @Setter
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EngagementOpening> engagementOpenings;

}
