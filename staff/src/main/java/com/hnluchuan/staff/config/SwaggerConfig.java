package com.hnluchuan.staff.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan("com.hnluchuan.staff.web.controller.api")
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	@SuppressWarnings("unchecked")
	@Bean
	public Docket addUserDocket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		ApiInfo apiInfo = new ApiInfo("API文档", "", "V1.0", "", null, "", "", new ArrayList<>());
		docket.apiInfo(apiInfo);
		// docket.select().apis(RequestHandlerSelectors.basePackage("com.hnluchuan.staff.web.controller.m")).build();
		docket.select().paths(Predicates.or(PathSelectors.any()));
		return docket;
	}
	
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		 registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	 }
}
