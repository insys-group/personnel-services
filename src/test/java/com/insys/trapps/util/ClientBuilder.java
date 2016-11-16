package com.insys.trapps.util;

import com.insys.trapps.model.Business;

public class ClientBuilder {
	
	private Business client=new Business();
	
	public static ClientBuilder buildClient(String comments) {
		ClientBuilder builder = new ClientBuilder();
		builder.client.setDescription(comments);
		return builder;
	}
	
	public static ClientBuilder buildClient(Business client) {
		ClientBuilder builder = new ClientBuilder();
		builder.client=client;
		return builder;
	}

	public Business getClient() {
		return client;
	}

	public void setClient(Business client) {
		this.client = client;
	}

	public Business build() {
		return client;
	}

	
}
