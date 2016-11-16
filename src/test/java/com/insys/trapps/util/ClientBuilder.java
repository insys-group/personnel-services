package com.insys.trapps.util;

import com.insys.trapps.model.Client;

public class ClientBuilder {
	
	private Client client=new Client();
	
	public static ClientBuilder buildClient(String comments) {
		ClientBuilder builder = new ClientBuilder();
		builder.client.setDescription(comments);
		return builder;
	}
	
	public static ClientBuilder buildClient(Client client) {
		ClientBuilder builder = new ClientBuilder();
		builder.client=client;
		return builder;
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
