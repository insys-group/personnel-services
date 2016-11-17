package com.insys.trapps.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.BusinessEntityType;
import com.insys.trapps.model.Client;
import com.insys.trapps.respositories.ClientRepository;
import com.insys.trapps.util.Builder;


@RestController
@RequestMapping("/client")
public class ClientController {
	
	 @Autowired
	 ClientRepository clientRepository;
	 
	
	@RequestMapping("/")
	public List<Iterable<Client>> listClients() {
		return Arrays.asList(clientRepository.findAll());
	}
	
	@RequestMapping("/create")
	public Client createClient() {
		Address address_1 = new Address("Insys Street", "Denver", "CO", "80014");
		Address address_2 = new Address("Luxoft Street", "Seattle", "WA", "70014");

		Client client = Builder.buildClient("test", "testing-denver",  BusinessEntityType.CONSULTING_TYPE).addLocation(address_1).addLocation(address_2).build();
		
		return clientRepository.save(client);
				
	}
}
