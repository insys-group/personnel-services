package com.insys.trapps;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.config.filter.CORSResponseFilter;

@SpringBootApplication
@EnableResourceServer
@Configuration
@RestController
public class TrappsApiApplication {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		SpringApplication.run(TrappsApiApplication.class, args);
	}
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@Bean
    public FilterRegistrationBean corsFilterBean(@Autowired CORSResponseFilter filter) {
		logger.debug("Enter: CORSConfiguration.corsFilter()");
        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        //bean.addUrlPatterns("/restlogin");
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
