/**
 * 
 */
package com.insys.trapps.config.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author msabir
 *
 */
@Component
public class CORSResponseFilter implements Filter {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Value("${trapps.client.apps}")
	private String[] clientApps;
	
	private static final List<String> exposedHeaders=Arrays.asList("X-XSRF-TOKEN", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", 
    		"Access-Control-Expose-Headers", "Access-Control-Request-Method", 
    		"Access-Control-Request-Headers", "Content-Type", "X-Requested-With", 
    		"accept", "Origin");

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    	logger.debug("Enter: CORSResponseFilter.init()");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
    	logger.debug("Enter: CORSResponseFilter.doFilter()");
        HttpServletResponse response=(HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", Arrays.stream(clientApps).collect(Collectors.joining(", ")));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT");
        //response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", exposedHeaders.stream().collect(Collectors.joining(", ")));
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {}

}