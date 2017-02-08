package com.insys.trapps.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.insys.trapps.config.filter.CORSResponseFilter;

//@Configuration
public class CORSConfiguration {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${trapps.client.apps}")
	private String[] clientApps;
	
	private static final List<String> exposedHeaders=Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", 
    		"Access-Control-Expose-Headers", "Access-Control-Request-Method", 
    		"Access-Control-Request-Headers", "Content-Type", "X-Requested-With", 
    		"accept", "Origin");
	/*
	@Bean
    public FilterRegistrationBean corsFilterBean() {
		logger.debug("Enter: CORSConfiguration.corsFilter()");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        for(String app: clientApps) {
        	logger.debug("adding app " + app);
        	config.addAllowedOrigin(app);
        }
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        exposedHeaders.forEach(header -> config.addAllowedHeader(header));
        config.setExposedHeaders(exposedHeaders);
        source.registerCorsConfiguration("/restlogin", config);
        CorsFilter filter=new CorsFilter(source);
        filter.setCorsProcessor(new TrappsCorsProcessor());
        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
	*/	
}




	/*
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		logger.debug("Enter: CORSConfiguration.addCorsMapping() ************************" + Arrays.toString(this.clientApps));
        registry.addMapping("/**")
        .allowedOrigins(clientApps)
        .allowedMethods("GET", "PUT", "POST", "DELETE")
        .allowCredentials(true)
        .allowedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", 
        		"Access-Control-Expose-Headers", "Access-Control-Request-Method", 
        		"Access-Control-Request-Headers", "Content-Type", "X-Requested-With", 
        		"accept", "Origin")
        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
        .maxAge(3600);
    }
	*/