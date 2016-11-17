package com.insys.trapps.util;

import com.insys.trapps.model.Business;

public class BusinessBuilder {

	private Business business = new Business();

	public static BusinessBuilder buildEntity(String comments) {
		BusinessBuilder builder = new BusinessBuilder();
		builder.business.setDescription(comments);
		return builder;
	}

	public static BusinessBuilder buildEntity(Business entity) {
		BusinessBuilder builder = new BusinessBuilder();
		builder.business = entity;
		return builder;
	}

	public Business build() {
		return business;
	}

}
