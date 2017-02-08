/**
 * 
 */
package com.insys.trapps.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.DefaultCorsProcessor;

/**
 * @author msabir
 *
 */
public class TrappsCorsProcessor extends DefaultCorsProcessor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected boolean handleInternal(ServerHttpRequest request, ServerHttpResponse response,
			CorsConfiguration config, boolean preFlightRequest) throws IOException {
		logger.debug("Enter: TrappsCorsProcessor.handleInternal()");
		return super.handleInternal(request, response, config, preFlightRequest);
	}
	
	@Override
	public boolean processRequest(CorsConfiguration config, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.debug("Enter: TrappsCorsProcessor.processRequest()");
		return super.processRequest(config, request, response);
	}

}
