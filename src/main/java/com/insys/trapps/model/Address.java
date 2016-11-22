package com.insys.trapps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * {@link Address Entity} for PersonellServices.
 *
 * @author Kris Krishna
 * @since 1.0.0
 **/
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    @NotNull
    private String address1;
    private String address2;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String zipCode;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Location location;

    /**
     * @return the addressId
     */
    public Long getAddressId() {
	return addressId;
    }

    /**
     * @param addressId
     *            the addressId to set
     */
    public void setAddressId(Long addressId) {
	this.addressId = addressId;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
	return address1;
    }

    /**
     * @param address1
     *            the address1 to set
     */
    public void setAddress1(String address1) {
	this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
	return address2;
    }

    /**
     * @param address2
     *            the address2 to set
     */
    public void setAddress2(String address2) {
	this.address2 = address2;
    }

    /**
     * @return the city
     */
    public String getCity() {
	return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
	this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
	return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
	this.state = state;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
	return zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
	return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(Location location) {
	this.location = location;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Address other = (Address) obj;
	if (address1 == null) {
	    if (other.address1 != null)
		return false;
	} else if (!address1.equals(other.address1))
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Address [address_id=" + addressId + ", street=" + address1 + ", city=" + city + ", state=" + state
		+ ", zip=" + zipCode + "]";
    }

}