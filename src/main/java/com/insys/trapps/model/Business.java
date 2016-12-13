package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "BUSINESS")
@EqualsAndHashCode(exclude = {"name"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Business extends AbstractEntity {
    @Getter
    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Getter
    @Setter
    @Column(name = "BUSINESS_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Getter
    @Setter
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Person> persons;

    @Getter
    @Setter
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Opportunity> opportunities;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "LOCATION"
            , joinColumns = @JoinColumn(name = "BUSINESS_ID", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    )
    private Set<Address> addresses;
}
