package com.insys.trapps.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.BusinessEntityType;
import com.insys.trapps.model.Client;
import com.insys.trapps.model.Location;
import com.insys.trapps.respositories.AddressRepository;
import com.insys.trapps.respositories.ClientRepository;
import com.insys.trapps.respositories.LocationRepository;


@RestController
@RequestMapping("/client")
public class ClientController {
	
	 @Autowired
	 ClientRepository clientRepository;
	 
	 @Autowired
	 LocationRepository locationRepository;
	 
	 @Autowired
	 AddressRepository addressRepository;
	
	@RequestMapping("/")
	public List<Iterable<Client>> listClients() {
		return Arrays.asList(clientRepository.findAll());
	}
	
	@RequestMapping("/create")
	public Client createClient() {
		Address address_1 = new Address(Long.valueOf(1), "Insys Street", "Denver", "CO", "80014");
		Location location_1 = new Location(Long.valueOf(1), address_1);
		
		Address address_2 = new Address(Long.valueOf(2), "Luxoft Street", "Seattle", "WA", "70014");
		Location location_2 = new Location(Long.valueOf(2), address_2);
		Collection<Location> locations = Arrays.asList(location_1, location_2);
		
		//addressRepository.save(address_1);
		//addressRepository.save(address_2);
		
		//locationRepository.save(locations);
		
		return clientRepository.save(new Client(
				           "test", "testing-denver business", 
				          BusinessEntityType.CONSULTING_TYPE,  locations));
				
	}
}
