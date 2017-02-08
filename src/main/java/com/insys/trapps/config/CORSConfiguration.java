package com.insys.trapps.config;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Configuration
public class CORSConfiguration {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${trapps.client.apps}")
	private String[] clientApps;
	
	private static final List<String> exposedHeaders=Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", 
    		"Access-Control-Expose-Headers", "Access-Control-Request-Method", 
    		"Access-Control-Request-Headers", "Content-Type", "X-Requested-With", 
    		"accept", "Origin");
	
	//@Bean
    public FilterRegistrationBean corsFilter() {
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
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(-2);
        return bean;
    }
	/*
	@Bean
	public Filter corsResponseHeaderFilter() {
		return new CorsResponseHeaderFilter(exposedHeaders, clientApps);
	}
	*/
	static class CorsResponseHeaderFilter implements Filter {
		private Logger logger=LoggerFactory.getLogger(this.getClass());
		private List<String> exposedHeaders;
		private String[] clientApps;
		
		
		public CorsResponseHeaderFilter(List<String> exposedHeaders, String[] clientApps) {
			logger.debug("CorsResponseHeaderFilter()");
			this.clientApps=clientApps;
			this.exposedHeaders=exposedHeaders;
		}
		
		@Override
		public void destroy() {}

		@Override
		public void init(FilterConfig arg0) throws ServletException {}
		  
	    @Override
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
	            throws ServletException, IOException {
	    	logger.debug("Enter: CorsResponseHeaderFilter.doFilter(");
	    	HttpServletRequest request=(HttpServletRequest)req;
	    	HttpServletResponse response=(HttpServletResponse)res;
	    	
	        if (request.getHeader("Access-Control-Request-Method") != null
	                && request.getMethod().contains("OPTIONS")) {
	        	logger.debug("OPTIONS request received********");
	        	
	            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	            response.addHeader("Access-Control-Allow-Headers", exposedHeaders.stream().collect(Collectors.joining(", ")));
	            response.addHeader("Access-Control-Allow-Origin", Arrays.stream(clientApps).collect(Collectors.joining(", ")));
	        }
	        filterChain.doFilter(request, response);
	    }
	}
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