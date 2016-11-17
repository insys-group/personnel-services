package com.insys.trapps.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long locationId;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn(name="address_id", nullable = false)
	private Address address;
	
	@ManyToOne
	@JoinColumn(name="business_id")
	 @JsonIgnore
	private Business business;
	
	public Location() {
		super();
	}


	public Location(Address address, Business business) {
		super();
		this.address = address;
		this.business = business;
	}


	public Long getLocationId() {
		return locationId;
	}


	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}



}
