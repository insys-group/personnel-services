package com.insys.trapps.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;;

/**
 * {@link Business Entity} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/


@Entity
@Table(name = "business_entity")
public class Business {

	// Client, Location and Address
	@Id
	@GeneratedValue
	private Long businessId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BusinessType businessType;

	@OneToMany(mappedBy = "business", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Location> locations;

	public Business() {
	}

	public Business(String name, String description, BusinessType businessType,
			Collection<Location> locations) {
		this.name = name;
		this.description = description;
		this.businessType = businessType;
		this.locations = locations;
	}


	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	public Collection<Location> getLocations() {
		return locations;
	}

	public void setLocations(Collection<Location> locations) {
		this.locations = locations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Business other = (Business) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Business [name=" + name + ", description=" + description
				+ ", businessType=" + businessType + ", locations=" + locations + "]";
	}

	

}
