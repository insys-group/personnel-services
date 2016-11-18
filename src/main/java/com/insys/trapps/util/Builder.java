package com.insys.trapps.util;

import java.util.HashSet;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Location;
import com.insys.trapps.respositories.LocationRepository;

/**
 * {@link Builder} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/

public class Builder {
	
	private Business business=new Business();
	
	public static Builder buildBusiness(String name, String description, BusinessType businesType) {
		Builder builder = new Builder();
		builder.business.setDescription(description);
		builder.business.setName(name);
		builder.business.setBusinessType(businesType);
		return builder;
	}
	
	public static Builder buildBusiness(Business business) {
		Builder builder = new Builder();
		builder.business=business;
		return builder;
	}
	
	public Builder addLocation(Address address) {
		if(business.getLocations()==null) {
			business.setLocations(new HashSet<>());
		}
		business.getLocations().add(new Location(address));
		return this;
	}

	public Business getClient() {
		return business;
	}

	public void setClient(Business business) {
		this.business = business;
	}

	public Business build() {
		return business;
	}

	
}
