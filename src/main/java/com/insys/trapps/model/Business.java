package com.insys.trapps.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;;

/**
 * {@link Business Entity} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/


@Entity
@Table(name = "BUSINESS")
@EqualsAndHashCode(of = {"name"})
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    protected Long id;

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
    @Column(name = "BUSINESS_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    //TODO - Enable when needed.
    /*
    @Getter
    @Setter
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Person> persons;
    
    //TODO - Enable when needed.
    @Getter
    @Setter
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Opportunity> opportunities;
     */
    @Getter
    @Setter
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Location> locations;
}
