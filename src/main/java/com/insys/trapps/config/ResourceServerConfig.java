package com.insys.trapps.config;

import com.insys.trapps.config.filter.CORSResponseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

/**
 * Created by msabir on 2/9/17.
 */

@EnableResourceServer
@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.data.rest.basePath}")
    private String restBasePath;

    @Value("${trapps.client-apps}")
    private String[] clientApp;


    private TokenStore tokenStore;

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
                .addFilterBefore(new CORSResponseFilter(clientApp), ChannelProcessingFilter.class)
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                    .mvcMatchers(HttpMethod.OPTIONS,"/api/**").permitAll()
                    .mvcMatchers("/api/**").fullyAuthenticated();
    }

}
