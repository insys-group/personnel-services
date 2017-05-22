package com.insys.trapps.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Address implements Serializable {

	private static final long serialVersionUID = 5861431468161799374L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NonNull
    @Column(name = "address_1", nullable = false)
    private String address1;

    @Getter
    @Setter
    @Column(name = "address_2")
    private String address2;

    @Getter
    @Setter
    @NonNull
    @Column(name = "city", nullable = false)
    private String city;

    @Getter
    @Setter
    @NonNull
    @Column(name = "state", nullable = false)
    private String state;

    @Getter
    @Setter
    @NonNull
    @Column(name = "zip_code", nullable = false)
    private String zipCode;
    
    @Getter
    @Setter
    @NonNull
    @Column(name = "country", nullable = false)
    private String country;
    
}

