package com.insys.trapps.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;;

@Entity
@Table(name = "client")
public class Client {
	
	//Client, Location and Address
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long businessEntityId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessEntityType businessType;
    
    @OneToMany(mappedBy="client", cascade =  CascadeType.ALL , fetch = FetchType.EAGER)
    private Collection<Location> locations;

   
	public Client() {
		super();
	}


	public Client(String name, String description, BusinessEntityType businessType,
			Collection<Location> locations) {
		super();
		this.name = name;
		this.description = description;
		this.businessType = businessType;
		this.locations = locations;
	}


	public Long getBusinessEntityId() {
		return businessEntityId;
	}


	public void setBusinessEntityId(Long businessEntityId) {
		this.businessEntityId = businessEntityId;
	}


	public BusinessEntityType getBusinessType() {
		return businessType;
	}


	public void setBusinessType(BusinessEntityType businessType) {
		this.businessType = businessType;
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
		Client other = (Client) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Client [businessEntityId=" + businessEntityId + ", name=" + name
				+ ", description=" + description + ", businessType=" + businessType
				+ ", locations=" + locations + "]";
	}


	
	
}
