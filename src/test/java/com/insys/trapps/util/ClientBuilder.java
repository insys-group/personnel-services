package com.insys.trapps.util;

import java.util.HashSet;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.BusinessEntityType;
import com.insys.trapps.model.Client;
import com.insys.trapps.model.Location;

public class ClientBuilder {
	
	private Client client=new Client();
	
	public static ClientBuilder buildClient(String name, String description, BusinessEntityType businesType) {
		ClientBuilder builder = new ClientBuilder();
		builder.client.setDescription(description);
		builder.client.setName(name);
		builder.client.setBusinessType(businesType);
		return builder;
	}
	
	public static ClientBuilder buildClient(Client client) {
		ClientBuilder builder = new ClientBuilder();
		builder.client=client;
		return builder;
	}
	
	public ClientBuilder addLocation(Address address) {
		if(client.getLocations()==null) {
			client.setLocations(new HashSet<>());
		}
		client.getLocations().add(new Location(address, client));
		return this;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client build() {
		return client;
	}

	
}
