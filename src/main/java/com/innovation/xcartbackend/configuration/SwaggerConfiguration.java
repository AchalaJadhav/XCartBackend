package com.innovation.xcartbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * A class used for configuring Swagger API documentation tool in the project.
 * @author Ajinkya.Deshpande
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	/**
	 * A method used to configure swagger in which the base package is declared.
	 * @return - Returns a docket type.
	 */
	@Bean
	public Docket swaggerAppConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.innovation.xcartbackend")).build();
	}
}
