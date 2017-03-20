package com.insys.trapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@SpringBootApplication
public class TrappsApiApplication {

	private DataSource dataSource;

	public TrappsApiApplication(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(TrappsApiApplication.class, args);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(this.dataSource);
	}

}
