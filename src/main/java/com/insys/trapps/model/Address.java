package com.insys.trapps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "ADDRESS")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    protected Long id;

    @Getter
    @Setter
    @NonNull
    @Column(name = "ADDRESS_1", nullable = false)
    private String address1;

    @Getter
    @Setter
    @Column(name = "ADDRESS_2")
    private String address2;

    @Getter
    @Setter
    @NonNull
    @Column(name = "CITY", nullable = false)
    private String city;

    @Getter
    @Setter
    @NonNull
    @Column(name = "STATE", nullable = false)
    private String state;

    @Getter
    @Setter
    @NonNull
    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;
    
    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "ADDRESS_ID")
	private Location location;
}
