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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    protected Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "BUSINESS_ID")
    private Business business;
}
