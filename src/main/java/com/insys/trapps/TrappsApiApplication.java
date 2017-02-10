package com.insys.trapps;

import com.insys.trapps.controllers.security.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrappsApiApplication {
    @Autowired
    private LoginController loginServlet;

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		SpringApplication.run(TrappsApiApplication.class, args);
	}

	/*
	@Bean
	public ServletRegistrationBean loginServlet() {
		logger.debug("Enter: TrappsApiApplication.loginServlet()");
		return new ServletRegistrationBean(loginServlet,"/restlogin/*");
	}*/
}
