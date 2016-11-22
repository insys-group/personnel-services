package com.insys.trapps.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.insys.trapps.model.Address;

/**
 * @author Brad Starkenberg
 */
public class TestUtils {
	private static final Logger log = LoggerFactory.getLogger(TestUtils.class);
	
	public static Address createAddress(String address1, String city, String state, String zipCode) {
		Address address = new Address();
		address.setAddress1(address1);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zipCode);
		return address;
	}
}
