package com.insys.trapps.util;

import java.util.HashSet;

/**
 * {@link BusinessEntityBuilder} for PersonnelServices.
 * Use this to use a test BusinessEntity in all tests
 * @author Kris Krishna
 * @since 1.0.0
 **/

import com.insys.trapps.model.Address;
import com.insys.trapps.model.BusinessEntity;
import com.insys.trapps.model.BusinessEntityType;

public class BusinessBuilder {

    private BusinessEntity businessEntity;

    public static BusinessBuilder buildBusiness(String name, String description, BusinessEntityType businesType) {
        BusinessBuilder builder = new BusinessBuilder();
        builder.businessEntity = BusinessEntity.builder()
                .descr(description)
                .entityType(businesType)
                .name(name)
                .build();
        return builder;
    }

    public static BusinessBuilder buildBusiness(BusinessEntity BusinessEntity) {
        BusinessBuilder builder = new BusinessBuilder();
        builder.businessEntity = BusinessEntity;
        return builder;
    }

    public BusinessBuilder addAddress(Address address) {
        if (businessEntity.getAddresses() == null) {
            businessEntity.setAddresses(new HashSet<>());
        }
        businessEntity.getAddresses().add(address);
        return this;
    }

    public BusinessEntity getClient() {
        return businessEntity;
    }

    public void setClient(BusinessEntity businessEntity) {
        this.businessEntity = businessEntity;
    }

    public BusinessEntity build() {
        return businessEntity;
    }

}
