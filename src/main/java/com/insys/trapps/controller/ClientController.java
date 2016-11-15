package com.insys.trapps.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.Client;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@RequestMapping("/")
	public List<Client> listClients() {
		return null;
	}
}
