package com.insys.trapps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by msabir on 2/9/17.
 */
@EnableResourceServer
@Configuration
//@RestController
public class ResourceServerConfig extends ResourceServerConfigurerAdapter { //WebSecurityConfigurerAdapter {
    /*
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
    */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/api/**").fullyAuthenticated();
                //.and()
                //.authorizeRequests().antMatchers("/restlogin").permitAll();
                //.and()
                //.antMatcher("/restlogin").anonymous()

        //http.requestMatchers().antMatchers("").and().authorizeRequests().requestMatchers()
    }
    /*
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**"); // #3
    }*/

}
