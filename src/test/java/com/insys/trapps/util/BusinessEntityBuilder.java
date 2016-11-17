package com.insys.trapps.util;

import com.insys.trapps.model.BusinessEntity;

public class BusinessEntityBuilder {

	private BusinessEntity businessEntity = new BusinessEntity();

	public static BusinessEntityBuilder buildEntity(String comments) {
		BusinessEntityBuilder builder = new BusinessEntityBuilder();
		builder.businessEntity.setDescription(comments);
		return builder;
	}

	public static BusinessEntityBuilder buildEntity(BusinessEntity entity) {
		BusinessEntityBuilder builder = new BusinessEntityBuilder();
		builder.businessEntity = entity;
		return builder;
	}

	public BusinessEntity build() {
		return businessEntity;
	}

}
