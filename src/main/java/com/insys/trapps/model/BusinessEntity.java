package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "BUSINESS_ENTITY")
@EqualsAndHashCode(exclude = {"persons", "opportunities"    }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessEntity extends AbstractEntity {
    @Getter
    @Setter
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Setter
    @NonNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "LOCATION"
            , joinColumns = @JoinColumn(name = "BUSINESS_ENTITY_ID", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    )
    private Set<Address> addresses;
}
