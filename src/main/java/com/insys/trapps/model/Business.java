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

@Entity
@Table(name = "client")
public class Business {
	
	//Client, Location and Address
	@Id
    @GeneratedValue
    private Long business_entity_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessEntityType entity_type;
    
    @OneToMany(mappedBy="client", cascade =  CascadeType.ALL , fetch = FetchType.EAGER)
    private Collection<Location> locations;

   
	public Business() {
		super();
	}


	public Business(String name, String description,
			BusinessEntityType entity_type, Collection<Location> locations) {
		super();
		//this.business_entity_id = business_entity_id;
		this.name = name;
		this.description = description;
		this.entity_type = entity_type;
		this.locations = locations;
	}


	public Long getBusiness_entity_id() {
		return business_entity_id;
	}



	public void setBusiness_entity_id(Long business_entity_id) {
		this.business_entity_id = business_entity_id;
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




	public BusinessEntityType getEntity_type() {
		return entity_type;
	}


	public void setEntity_type(BusinessEntityType entity_type) {
		this.entity_type = entity_type;
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
		return "Client [business_entity_id=" + business_entity_id + ", name=" + name
				+ ", description=" + description + ", entity_type=" + entity_type
				+ ", locations=" + locations + "]";
	}
	
	
	
}
