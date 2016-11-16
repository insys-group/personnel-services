package com.insys.trapps.model;

/**
 * Created by vnalitkin on 11/17/2016.
 */
public enum BusinessEntityType {
    CONPANY("Company"),
    CONPANY1("Company1");

    private String businessEntityType;

    BusinessEntityType(String businessEntityType) {
        this.businessEntityType = businessEntityType;
    }

}
