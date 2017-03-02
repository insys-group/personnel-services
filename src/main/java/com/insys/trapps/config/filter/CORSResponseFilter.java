/**
 * 
 */
package com.insys.trapps.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author msabir
 *
 */
//@Component
public class CORSResponseFilter extends OncePerRequestFilter {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private String[] clientApps;

	public CORSResponseFilter(String[] clientApps) {
	    this.clientApps=clientApps;
    }

	private static final List<String> exposedHeaders=Arrays.asList("X-XSRF-TOKEN", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", 
    		"Access-Control-Expose-Headers", "Access-Control-Request-Method", 
    		"Access-Control-Request-Headers", "Content-Type", "X-Requested-With", 
    		"accept", "Origin", "Authorization", "x-filename");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("Enter: CORSResponseFilter.doFilter()");

        if(CorsUtils.isCorsRequest(request)){
            logger.debug("Enter: Processing CORS request" + request.getRequestURL() + " " + request.getRequestURI() + " " + request.getContextPath());
            response.addHeader("Access-Control-Allow-Origin", Arrays.stream(clientApps).collect(Collectors.joining(", ")));
//        }
//        if(CorsUtils.isPreFlightRequest(request)) {
            logger.debug("Enter: Processing PreFlight request" + request.getRequestURL() + " " + request.getRequestURI() + " " + request.getContextPath());
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT");
            //response.setHeader("Access-Control-Max-Age", "3600");
            response.addHeader("Access-Control-Allow-Headers", exposedHeaders.stream().collect(Collectors.joining(", ")));
            response.setStatus(HttpServletResponse.SC_OK);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}

}