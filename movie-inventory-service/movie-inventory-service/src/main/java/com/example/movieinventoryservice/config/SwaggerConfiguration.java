package com.example.movieinventoryservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.movieinventoryservice.properties.AppDevelopmentProperties;

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
public class SwaggerConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfiguration.class);

	@Autowired
	private AppDevelopmentProperties app;

	ApiInfo apiInfo() {
		LOGGER.info(app.toString());
		return new ApiInfoBuilder().title(this.app.getProject()).description(this.app.getDescription())
				.license(this.app.getLicence()).licenseUrl("").termsOfServiceUrl("")
				.version(this.app.getEnvironment() + "-" + this.app.getVersion())
				.contact(new Contact(this.app.getContact(), "", "")).build();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

}
