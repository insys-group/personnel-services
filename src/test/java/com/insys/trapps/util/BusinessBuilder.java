package com.insys.trapps.util;

import java.util.HashSet;

/**
 * {@link BusinessEntityBuilder} for PersonnelServices.
 * Use this to use a test BusinessEntity in all tests
 * @author Kris Krishna
 * @since 1.0.0
 **/

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;

public class BusinessBuilder {

    private Business business;

    public static BusinessBuilder buildBusiness(String name, String description, BusinessType businesType) {
        BusinessBuilder builder = new BusinessBuilder();
        builder.business = Business.builder()
                .description(description)
                .businessType(businesType)
                .name(name)
                .build();
        return builder;
    }

    public static BusinessBuilder buildBusiness(Business Business) {
        BusinessBuilder builder = new BusinessBuilder();
        builder.business = Business;
        return builder;
    }

    public BusinessBuilder addAddress(Address address) {
        if (business.getAddresses() == null) {
            business.setAddresses(new HashSet<>());
        }
        business.getAddresses().add(address);
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
