package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "ENGAGEMENT_OPENING")
@AllArgsConstructor
@NoArgsConstructor
public class EngagementOpening extends AbstractEntity {

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ENGAGEMENT_ID")
    private Engagement engagement;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column(name = "RATE")
    @Getter
    @Setter
    private BigDecimal rate;

    @Getter
    @Setter
    @OneToMany(mappedBy = "engagementOpening", cascade = CascadeType.ALL)
    private Set<Contract> contracts;

}