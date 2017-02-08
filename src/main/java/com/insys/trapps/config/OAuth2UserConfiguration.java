package com.insys.trapps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * @author msabir
 *
 */
@SuppressFBWarnings(value = {"BC_UNCONFIRMED_CAST_OF_RETURN_VALUE"})
@Configuration
public class OAuth2UserConfiguration extends GlobalAuthenticationConfigurerAdapter {
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("msabir").password("msabirpass").roles("USER").and()
			.withUser("admin").password("password").roles("ADMIN");
	}
}
