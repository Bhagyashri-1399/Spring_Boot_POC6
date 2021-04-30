package com.junit5.poc6.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//Enable Swagger
@EnableSwagger2
public class SwaggerConfig {
	// configuring the contact detail
	public static final Contact DEFAULT_CONTACT = 
			new Contact("Bhagyashri Mandlik", "https://www.neosofttech.com/","bhagyashri.mandlik@neosoftmail.com");
	// configuring DEFAULT_API_INFO
	public static final ApiInfo DEFAULT_API_INFO = 
			new ApiInfo("Spring_Boot_POC6_Demo", "Api Documentation Demo", "1.0",
			"urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<VendorExtension>());

	//creating bean
	@Bean
	public Docket api() {		
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO);
	}
}
