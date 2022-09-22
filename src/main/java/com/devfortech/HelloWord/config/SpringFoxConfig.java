package com.devfortech.HelloWord.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket swagger() throws Exception {
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.devfortech.HelloWord"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		 ApiInfo apiInfo = new ApiInfo(
	                "Produtos API REST",
	                "API REST de cadastro de produtos.",
	                "1.0",
	                "Terms of Service",
	                new Contact("Michelli Brito", "https://www.youtube.com/michellibrito",
	                        "michellidibrito@gmail.com"),
	                "Apache License Version 2.0",
	                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
	        );
		 
		 return apiInfo;
	}
}
