package com.insys.trapps.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;;

/**
 * {@link Business Entity} for PersonellServices.
 *
 * @author Kris Krishna
 * @since 1.0.0
 **/
@Entity
public class Business {

    // Client, Location and Address
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long businessId;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id", nullable = false)
    private Collection<Location> locations;

    /**
     * @return the businessId
     */
    public Long getBusinessId() {
	return businessId;
    }

    /**
     * @param businessId
     *            the businessId to set
     */
    public void setBusinessId(Long businessId) {
	this.businessId = businessId;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * @return the businessType
     */
    public BusinessType getBusinessType() {
	return businessType;
    }

    /**
     * @param businessType
     *            the businessType to set
     */
    public void setBusinessType(BusinessType businessType) {
	this.businessType = businessType;
    }

    /**
     * @return the locations
     */
    public Collection<Location> getLocations() {
	return locations;
    }

    /**
     * @param locations
     *            the locations to set
     */
    public void setLocations(Collection<Location> locations) {
	this.locations = locations;
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
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	Business other = (Business) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
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
	return "Business [name=" + name + ", description=" + description + ", businessType=" + businessType
		+ ", locations=" + locations + "]";
    }

}
