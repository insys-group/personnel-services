package com.insys.trapps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by vnalitkin on 11/23/2016.
 */
@Configuration
@Profile("in-memory")
public class LocalConfiguration {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder
                .setType(H2)
                .generateUniqueName(true)
                .addScript("localData.sql");
        return builder.build();
    }

}
