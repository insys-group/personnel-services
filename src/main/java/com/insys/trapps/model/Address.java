package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import javax.xml.crypto.Data;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "ADDRESS")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends AbstractEntity {

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
}
