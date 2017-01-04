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
@Table(name = "ADDRESS")
@EqualsAndHashCode(of = {"address1", "city", "state", "zipCode", "country"}, callSuper = false)
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
//
//    @Version
//    @Getter
//    @Setter
//    @Column(name = "VERSION")
//    private Long version;

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
    
    @Getter
    @Setter
    @NonNull
    @Column(name = "COUNTRY", nullable = false)
    private String country;
//    	
//	@PrePersist
//	public void init() {
//		if(this.version==null) {
//			this.version=1L;
//		}
//	}

    
}

