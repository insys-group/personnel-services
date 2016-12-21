/**
 * 
 */
package com.insys.trapps.config;

import org.springframework.data.rest.core.config.EnumTranslationConfiguration;
import org.springframework.data.rest.core.config.MetadataConfiguration;
import org.springframework.data.rest.core.config.ProjectionDefinitionConfiguration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;

/**
 * @author msabir
 *
 */
//@Component
public class TrappsRepositoryRestConfiguration extends RepositoryRestConfiguration {
	/**
	 * @param projectionConfiguration
	 * @param metadataConfiguration
	 * @param enumTranslationConfiguration
	 */
	public TrappsRepositoryRestConfiguration(ProjectionDefinitionConfiguration projectionConfiguration,
			MetadataConfiguration metadataConfiguration, EnumTranslationConfiguration enumTranslationConfiguration) {
		super(projectionConfiguration, metadataConfiguration, enumTranslationConfiguration);
	}

	@Override
	public boolean isIdExposedFor(Class<?> domainType) {
		return true;
	}
}

