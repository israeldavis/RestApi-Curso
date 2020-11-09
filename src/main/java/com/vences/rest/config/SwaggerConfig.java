package com.vences.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.vences.rest"))
				.paths(PathSelectors.ant("/users/**"))
				.build();
	}
	
	// Swagger Metadata: http://localhost:8080/v2/api-docs
	// Swagger UI URL: http://localhost:8080/v2/swagger-ui.html
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("User Management API Service")
				.description("Esta página lista toda el API para Administración de Usuarios")
				.version("2.0")
				.contact(new Contact("David Vences Mondragón", "http://www.citibanamex/RI", "israelvences@citi.com"))
				.license("Licence ")
				.licenseUrl("http://www.stacksimplify.com/licence.html")
				.build();
	}
	
}
