package com.insys.trapps;

import com.insys.trapps.model.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@SpringBootApplication
public class TrappsApiApplication {
//    @Autowired
//    private LoginController loginServlet;
//
	private DataSource dataSource;
	private Logger logger=LoggerFactory.getLogger(this.getClass());

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
