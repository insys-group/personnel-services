package com.insys.trapps.config;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.insys.trapps.model.AbstractEntity;

/**
 * Created by vnalitkin on 12/4/16.
 */
@Configuration
public class RestConfiguration extends RepositoryRestConfigurerAdapter {
	private Logger logger=Logger.getLogger(RestConfiguration.class);
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setReturnBodyForPutAndPost(true);
        config.setReturnBodyOnCreate(true);

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        Set<BeanDefinition> components = provider.findCandidateComponents("com.insys.trapps.model");

        List<Class<?>> classes = new ArrayList<>();

        components.forEach(component -> {
            try {
                classes.add(Class.forName(component.getBeanClassName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        classes.add(AbstractEntity.class);
        config.exposeIdsFor(classes.toArray(new Class[classes.size()]));
        /*
        classes.forEach(cls -> {
        	logger.debug("Class registering " + cls.getName());
        	config.exposeIdsFor(cls);
        });
        */
    }
    
}