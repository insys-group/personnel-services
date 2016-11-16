package com.insys.trapps.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue
	private Long location_id;
	
	@OneToOne(mappedBy="location", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	private Address address;
	
	@ManyToOne
	@JoinColumn(name="business_entity_id")
	private Client client;
	
	public Location() {
		super();
	}


	public Location(Long location_id, Address address) {
		super();
		//this.location_id = location_id;
		this.address = address;
	}




	public Long getLocation_id() {
		return location_id;
	}


	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		}
		else if (!address.equals(other.address))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Location [location_id=" + location_id + ", address=" + address
				+ ", client=" + client + "]";
	}


}
