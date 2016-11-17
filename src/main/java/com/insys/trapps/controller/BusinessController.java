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
import com.insys.trapps.util.Builder;

@RestController
@RequestMapping("/business")
public class BusinessController {

	@Autowired
	BusinessRepository businessRepository;


	@RequestMapping("/")
	public List<Iterable<Business>> listBusinesses() {
		return Arrays.asList(businessRepository.findAll());
	}

	@RequestMapping("/create")
	public Business createBusiness() {
		Address address_1 = new Address("Insys Street", "Denver", "CO", "80014");
		Address address_2 = new Address("Luxoft Street", "Seattle", "WA", "70014");

		Business client = Builder.buildBusiness("test", "testing-denver",  BusinessType.INSYS).addLocation(address_1).addLocation(address_2).build();
		
		return businessRepository.save(client);
	}
}
