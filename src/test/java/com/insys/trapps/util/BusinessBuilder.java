package com.insys.trapps.util;

import java.util.HashSet;

/**
 * {@link BusinessBuilder} for PersonnelServices.
 * Use this to use a test business in all tests
 * @author  Kris Krishna
 * @since 1.0.0
**/

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Location;

public class BusinessBuilder {

	private Business business=new Business();
	
	public static BusinessBuilder buildBusiness(String name, String description, BusinessType businesType) {
		BusinessBuilder builder = new BusinessBuilder();
		builder.business.setDescr(description);
		builder.business.setName(name);
		builder.business.setBusinessType(businesType);
		return builder;
	}
	
	public static BusinessBuilder buildBusiness(Business business) {
		BusinessBuilder builder = new BusinessBuilder();
		builder.business=business;
		return builder;
	}
	
	public BusinessBuilder addLocation(Address address) {
		if(business.getLocations()==null) {
			business.setLocations(new HashSet<>());
		}
		business.getLocations().add(Location.builder().address(address).build());
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
