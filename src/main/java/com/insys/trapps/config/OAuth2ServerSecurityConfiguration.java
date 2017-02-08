package com.insys.trapps.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class OAuth2ServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${trapps.client.apps}")
	private String[] clientApps;
	
	private static final List<String> exposedHeaders=Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", 
    		"Access-Control-Expose-Headers", "Access-Control-Request-Method", 
    		"Access-Control-Request-Headers", "Content-Type", "X-Requested-With", 
    		"accept", "Origin", "X-XSRF-TOKEN");
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .cors().and()
        .authorizeRequests()
            .antMatchers("/api/**").authenticated();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/restlogin");
        //.and().ignoring().antMatchers(HttpMethod.OPTIONS, "/restlogin");
    }
    
    @Bean
	CorsConfigurationSource corsConfigurationSource() {
		logger.debug("Enter: OAuth2ServerSecurityConfiguration.corsConfigurationSource()");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList(clientApps));
		config.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "HEAD", "OPTIONS", "DELETE"));
        source.registerCorsConfiguration("/**", config);
        
        //exposedHeaders.forEach(header -> config.addAllowedHeader(header));
        //config.setExposedHeaders(exposedHeaders);
        
        
        return source;
        /*
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
		*/
	}
    
    
}