package com.insys.trapps.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * Created by msabir on 2/9/17.
 */

@EnableResourceServer
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RestController
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private TokenStore tokenStore;
    @RequestMapping("/user")
    public Principal user(Principal user) {
        logger.debug("Enter: ResourceServerConfig.user()");
        return user;
    }
    public ResourceServerConfig(TokenStore tokenStore) {
        this.tokenStore=tokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(this.tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/restlogin/**").permitAll()
                .antMatchers("/api/**").fullyAuthenticated()
                .anyRequest().authenticated();

        /*
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/api/**").fullyAuthenticated();
        */
    }
}
