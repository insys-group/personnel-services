package com.insys.trapps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "ADDRESS")
@EqualsAndHashCode(of = {"address1", "city", "state"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "ADDRESS_1", nullable = false)
    private String address1;

    @Getter
    @Setter
    @Column(name = "ADDRESS_2")
    private String address2;

    @Getter
    @Setter
    @Column(name = "CITY", nullable = false)
    private String city;

    @Getter
    @Setter
    @Column(name = "STATE", nullable = false)
    private String state;

    @Getter
    @Setter
    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;
}
