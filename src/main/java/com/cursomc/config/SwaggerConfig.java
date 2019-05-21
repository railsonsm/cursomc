package com.cursomc.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}
	
	private SecurityContext securityContext() {
	    return SecurityContext.builder()
	      .securityReferences(
	        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
	      .forPaths(PathSelectors.regex("/foos.*"))
	      .build();
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().clientId("teste").clientSecret("teste").scopeSeparator(" ")
				.useBasicAuthenticationWithAccessCodeGrant(true).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service",
				new Contact("John Doe", "www.example.com", "myeaddress@company.com"), "License of API", "API license URL", Collections.emptyList());
	}

	private SecurityScheme securityScheme() {
		GrantType grantType = new AuthorizationCodeGrantBuilder().tokenEndpoint(new TokenEndpoint("http://localhost:8081/" + "login", "oauthtoken"))
				.tokenRequestEndpoint(new TokenRequestEndpoint("http://localhost:8081/" + "login", "teste", "teste")).build();

		SecurityScheme oauth = new OAuthBuilder().name("spring_oauth").grantTypes(Arrays.asList(grantType)).scopes(Arrays.asList(scopes())).build();
		return oauth;
	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = { new AuthorizationScope("read", "for read operations"), new AuthorizationScope("write", "for write operations"),
				new AuthorizationScope("foo", "Access foo API") };
		return scopes;
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
