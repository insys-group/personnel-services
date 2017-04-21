package com.insys.trapps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * Created by areyna on 4/20/17.
 */
@Configuration
public class MailConfig {

    @Bean(name ="freemarkerConfiguration")
    public FreeMarkerConfigurationFactoryBean freemarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean freemarkerConfiguration = new FreeMarkerConfigurationFactoryBean();
        freemarkerConfiguration.setTemplateLoaderPath("classpath:/templates/mail");
        return freemarkerConfiguration;
    }

}
