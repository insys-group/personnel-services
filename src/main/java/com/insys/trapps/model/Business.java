package com.insys.trapps.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.insys.trapps.model.Opportunity.OpportunityBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;;

/**
 * {@link Business Entity} for PersonnelServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/


@Entity
@Table(name = "BUSINESS")
@EqualsAndHashCode(of = {"name"})
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
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

    /*@Getter
    @Setter
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Person> persons;
    
    @Getter
    @Setter
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Opportunity> opportunities;*/
     
   @Getter
   @Setter  
   @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "BUSINESS_ID")
    private Set<Location> locations;
}
