package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "BUSINESS_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
public class BusinessEntity extends AbstractEntity {
    @Getter
    @Setter
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Setter
    @NonNull
    @Column(name = "DESCR", nullable = false)
    private String descr;

    @Getter
    @Setter
    @NonNull
    @Column(name = "ENTITY_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessEntityType entityType;

    @Getter
    @Setter
    @OneToMany(mappedBy = "businessEntity", cascade = CascadeType.ALL)
    private Set<Person> persons;

    @Getter
    @Setter
    @OneToMany(mappedBy = "businessEntity", cascade = CascadeType.ALL)
    private Set<Opportunity> opportunities;

    @Getter
    @Setter
    @OneToMany(mappedBy = "businessEntity", cascade = CascadeType.ALL)
    private Set<Location> locations;
}
