package com.insys.trapps.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.Location;
import com.insys.trapps.respositories.AddressRepository;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.LocationRepository;

@RestController
@RequestMapping("/business-entity")
public class BusinessController {

	@Autowired
	BusinessRepository businessRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	AddressRepository addressRepository;

	@RequestMapping("/")
	public List<Iterable<Business>> listBusinesses() {
		return Arrays.asList(businessRepository.findAll());
	}

	@RequestMapping("/create")
	public Business createBusiness() {
		// TODO: Implement
		return null;
	}
}
