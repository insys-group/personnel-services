package com.insys.trapps.util;

import com.insys.trapps.model.BusinessEntity;

public class BusinessEntityBuilder {

	private BusinessEntity businessEntity = new BusinessEntity();

	public static BusinessEntityBuilder buildClient(String comments) {
		BusinessEntityBuilder builder = new BusinessEntityBuilder();
		builder.businessEntity.setDescription(comments);
		return builder;
	}

	public static BusinessEntityBuilder buildClient(BusinessEntity entity) {
		BusinessEntityBuilder builder = new BusinessEntityBuilder();
		builder.businessEntity = entity;
		return builder;
	}

	public BusinessEntity getClient() {
		return businessEntity;
	}

	public void setClient(BusinessEntity entity) {
		this.businessEntity = entity;
	}

	public BusinessEntity build() {
		return businessEntity;
	}

}
