package com.insys.trapps.config;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author msabir
 *
 */
@SuppressFBWarnings(value = {"BC_UNCONFIRMED_CAST_OF_RETURN_VALUE"})
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
    @Value("${security.oauth.client-info.client-id}")
	private String clientId;

    @Value("${security.oauth.client-info.client-secret}")
	private String clientSecret;

    @Value("${security.oauth.client-info.authorized-grant-types}")
	private String[] grantTypes;

    @Value("${security.oauth.client-info.scope}")
	private String[] scopes;
    */

    private AuthenticationManager authenticationManager;

    private ClientDetailsService clientDetailsService;
	private UserDetailsService userDetailsService;
	private TokenStore tokenStore;

	public AuthServerConfig(AuthenticationManager authenticationManager,
                            @Qualifier("ClientDetailsServiceImpl")
                            ClientDetailsService clientDetailsService,
                            UserDetailsService userDetailsService,
                            TokenStore tokenStore) {
	    this.authenticationManager=authenticationManager;
	    this.clientDetailsService=clientDetailsService;
		this.userDetailsService=userDetailsService;
		this.tokenStore=tokenStore;
	}

    @Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		logger.debug("Enter: AuthServerConfig.configure()");
		endpoints.userDetailsService(this.userDetailsService)
                .tokenStore(this.tokenStore)
                .authenticationManager(this.authenticationManager);
		//endpoints.setClientDetailsService(this.clientDetailsService);
	}

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	    logger.debug("Enter: AuthServerConfig.configure()");
	    clients.withClientDetails(this.clientDetailsService);
//        clients.inMemory()
//                .withClient(clientId)
//                .authorizedGrantTypes(grantTypes)
//                .secret(clientSecret)
//                .scopes(scopes);
    }
}
/*
public class AuthServerConfig extends GlobalAuthenticationConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserDetailsService userDetailsService;

	public AuthServerConfig(UserDetailsService userDetailsService) {
		this.userDetailsService=userDetailsService;
	}

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("Enter: AuthServerConfig.init()");
		auth.userDetailsService(this.userDetailsService);
//				.inMemoryAuthentication()
//			.withUser("msabir").password("msabirpass").roles("USER").and()
//			.withUser("admin").password("password").roles("ADMIN");
	}
}*/