package com.mka;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mka.repository.UserRepository;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories("com.mka.repository")
public class AppConfig {


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
   
	 @Bean
	    public Docket newsApi() {
	    	List<Parameter> paramList = new ArrayList<>();
	        
			/*paramList.add(new ParameterBuilder()
					.name("X-Auth-Token")
			        .description("API authentication token")
			        .modelRef(new ModelRef("string"))
			        .parameterType("header")
			        .required(true)
					.build());*/
	    	
	    
	        return new Docket(DocumentationType.SWAGGER_2)
	        		 .globalOperationParameters(paramList)
	                .groupName("IOT server")
	                .apiInfo(apiInfo())
	                .select()
	                .paths(regex("/api/v1/.*"))
	               
	                .build();
	        
	    }
	    
	     
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Iot API")
	                .description("Iot API documentation").build();
	    }
   
}