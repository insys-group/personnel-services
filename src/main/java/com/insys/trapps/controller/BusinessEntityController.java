package com.insys.trapps.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.BusinessEntityType;
import com.insys.trapps.model.BusinessEntity;
import com.insys.trapps.model.Location;
import com.insys.trapps.respositories.AddressRepository;
import com.insys.trapps.respositories.BusinessEntityRepository;
import com.insys.trapps.respositories.LocationRepository;

@RestController
@RequestMapping("/business-entity")
public class BusinessEntityController {

	@Autowired
	BusinessEntityRepository businessEntityRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	AddressRepository addressRepository;

	@RequestMapping("/")
	public List<Iterable<BusinessEntity>> listBusinessEntities() {
		return Arrays.asList(businessEntityRepository.findAll());
	}

	@RequestMapping("/create")
	public BusinessEntity createBusinessEntity() {
		Address address = new Address(Long.valueOf(1), "Insys Street", "Denver", "CO", "80014");
		Location location = new Location(Long.valueOf(1), address);

		Address address1 = new Address(Long.valueOf(2), "Luxoft Street", "Seattle", "WA", "70014");
		Location location1 = new Location(Long.valueOf(2), address1);
		Collection<Location> locations = Arrays.asList(location, location1);

		// TODO: Save functions for address repo
		// addressRepository.save(address_1);
		// addressRepository.save(address_2);
		// locationRepository.save(locations);

		return businessEntityRepository.save(
				new BusinessEntity("test", "testing-denver business", BusinessEntityType.CONSULTING_TYPE, locations));

	}
}
