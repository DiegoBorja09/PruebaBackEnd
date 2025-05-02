package com.test.diego.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para el bean ModelMapper, que se encarga de mapear
 * objetos. Define la configuración personalizada del ModelMapper y crea un bean
 * para su uso en la aplicación.
 * 
 * @author
 */
@Configuration
public class ModelMapperConfig {

	/**
	 * Crea y configura un bean ModelMapper con opciones personalizadas.
	 *
	 * @return Un objeto ModelMapper configurado según las especificaciones.
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				.setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
		return modelMapper;
	}
}
