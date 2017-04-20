package com.insys.trapps.config;

import com.insys.trapps.model.*;
import com.insys.trapps.model.interview.Feedback;
import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.model.interview.InterviewTemplate;
import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.person.PersonSkill;
import com.insys.trapps.model.security.Client;
import com.insys.trapps.model.security.User;
import com.insys.trapps.security.Authorities;
import com.insys.trapps.security.CustomAuthenticationEntryPoint;
import com.insys.trapps.security.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.persistence.Entity;
import javax.sql.DataSource;

@Configuration
public class OAuth2Configuration {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Autowired
        private CustomLogoutSuccessHandler customLogoutSuccessHandler;

        @Bean
        public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");
            source.registerCorsConfiguration("/**", config);
            CorsFilter corsFilter = new CorsFilter(source);
            return corsFilter;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {


            http
                    .cors().and()
                    .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                    .and().logout()
                    .logoutUrl("/oauth/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .and().csrf()
                    .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                    .disable()
                    .headers()
                    .frameOptions().disable()
                    //.sessionManagement()
                    //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/v1/**").authenticated();
        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter implements EnvironmentAware {

        private static final String ENV_OAUTH = "authentication.oauth.";
        private static final String PROP_CLIENTID = "clientid";
        private static final String PROP_SECRET = "secret";
        private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";

        private RelaxedPropertyResolver propertyResolver;

        @Autowired
        private DataSource dataSource;

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient(propertyResolver.getProperty(PROP_CLIENTID))
                    .scopes("read", "write")
                    .authorities(Authorities.ADMIN.name())
                    .authorizedGrantTypes("password", "refresh_token")
                    .secret(propertyResolver.getProperty(PROP_SECRET))
                    .accessTokenValiditySeconds(propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 6000));
        }

        @Override
        public void setEnvironment(Environment environment) {
            this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
        }

    }

    @Configuration
    public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            ClassPathScanningCandidateComponentProvider provider
                    = new ClassPathScanningCandidateComponentProvider(false);
            provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
            makeIdReturnedFor("com.insys.trapps.model", config, provider);
        }

        private void makeIdReturnedFor(String pakage, RepositoryRestConfiguration config, ClassPathScanningCandidateComponentProvider provider) {
            for (BeanDefinition beanDefinition : provider.findCandidateComponents(pakage)) {
                try {
                    Class<?> entity = Class.forName(beanDefinition.getBeanClassName());
                    config.exposeIdsFor(entity);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
