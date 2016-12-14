package com.insys.trapps.config;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by vnalitkin on 12/4/16.
 */
@Configuration
public class RestConfiguration extends RepositoryRestConfigurerAdapter {

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

        config.exposeIdsFor(classes.toArray(new Class[classes.size()]));
    }

}