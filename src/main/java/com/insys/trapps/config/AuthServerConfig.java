package com.insys.trapps.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * @author msabir
 *
 */
@SuppressFBWarnings(value = {"BC_UNCONFIRMED_CAST_OF_RETURN_VALUE"})
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends GlobalAuthenticationConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("Enter: AuthServerConfig.init()");
		auth.inMemoryAuthentication()
			.withUser("msabir").password("msabirpass").roles("USER").and()
			.withUser("admin").password("password").roles("ADMIN");
	}
}
