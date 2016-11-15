package com.insys.trapps.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.Client;
import com.insys.trapps.respositories.ClientRepository;


@RestController
@RequestMapping("/client")
public class ClientController {
	
	 @Autowired
	 ClientRepository repository;
	
	@RequestMapping("/")
	public List<Iterable<Client>> listClients() {
		return Arrays.asList(repository.findAll());
	}
	
	@RequestMapping("/create")
	public Client createClient() {
		return repository.save(new Client(
				          Long.valueOf(1), "test", "testing", "denver", 
				         "MZ", "303-2682717", new String[]{"kkrishna@insys.com"}, "Insys-drive", 
				         new String[]{"personnel-srvc"}, new String[]{"project-1"}, true, "2017"));
				
	}
}
